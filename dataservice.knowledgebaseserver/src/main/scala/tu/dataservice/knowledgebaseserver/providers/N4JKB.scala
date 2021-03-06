
package tu.dataservice.knowledgebaseserver.providers

import org.neo4j.graphdb.{Relationship, Transaction, RelationshipType, Node}
import tu.model.knowledge._
import org.slf4j.LoggerFactory
import com.typesafe.config.ConfigFactory
import org.neo4j.kernel.EmbeddedGraphDatabase
import collection.mutable
import collection.immutable.HashMap
import org.neo4j.graphdb.index.Index
import org.neo4j.cypher.commands.Return
import java.io.File
import tools.nsc.io.Directory

/**
 *
 * @author Alexander Toschev
 *          Date: 10/30/12
 *          Time: 5:51 AM
 *
 */
object N4JKB extends KB {
  val log = LoggerFactory.getLogger(this.getClass)
  val defaultFilename = {
    //load file name or use default
    val conf = ConfigFactory.load()
    val suffix = conf.getString("tu.knowledgebaseserver.dir")
    if (suffix.size == 0)
      java.lang.System.getProperty("user.home") + "/" + "tu_kb"
    else if (suffix.charAt(0) == '/' || suffix.charAt(2) == ':')
      suffix
    else
      java.lang.System.getProperty("user.home") + "/" + suffix
  }
  val keyField = "key"
  private var inited = false
  private var _GraphDb: EmbeddedGraphDatabase = _
  private var _nodeIndex: IndexedSeq[Node] = _

  def apply(): EmbeddedGraphDatabase = {
    if (!inited) {

      _GraphDb = new EmbeddedGraphDatabase(defaultFilename)
      log.trace("Neo4j database initialized. Location "+defaultFilename)
      ShutdownHook(_GraphDb.shutdown())
      //TODO: _nodeIndex = _GraphDb.index().forNodes( "nodes" );
      inited = true
    }

    _GraphDb
  }

  override def saveResource(resource: Resource, key: String, linkType: String): Boolean = {
    saveResource(resource, N4JKB().getReferenceNode, key, linkType)
  }

  override def loadChild(key: String, linkType: String): Map[String, String] = loadChild(N4JKB().getReferenceNode, key, linkType)

  override def loadChildrenList(linkType: String): List[Map[String, String]] = loadChildrenList(N4JKB().getReferenceNode, linkType)

  override def loadChildrenMap(linkType: String): Map[String, Map[String, String]] = loadChildrenMap(N4JKB().getReferenceNode, linkType)


  override def saveResource(child: Resource, parent: KBNodeId, key: String, linkType: String = "defaultLink"): Boolean = {
    saveResource(child, getNodeById(parent.ID), key, linkType)
  }

  override def loadChild(parent: KBNodeId, key: String, linkType: String): Map[String, String] = loadChild(getNodeById(parent.ID), key, linkType)

  override def loadChildrenList(parent: KBNodeId, linkType: String): List[Map[String, String]] = loadChildrenList(getNodeById(parent.ID), linkType)

  override def loadChildrenMap(parent: KBNodeId, linkType: String): Map[String, Map[String, String]] = loadChildrenMap(getNodeById(parent.ID), linkType)


  private def getNodeById(Id: Long): Node = {
    try {
      return N4JKB().getNodeById(Id)
    }
    catch {
      case _ => LoggerFactory.getLogger(this.getClass).error("Attempt to get not existing node with ID {}", Id.toString)
    }
    N4JKB().getReferenceNode
  }

  def createLink(parent: KBNodeId, child:KBNodeId,linkType: String, key:String)={
    var ok = false
    val tx: Transaction = N4JKB().beginTx()
    try {
    val parentNode = N4JKB().getNodeById(parent.ID)
    val childNode = N4JKB().getNodeById(child.ID)
    val relationship = parentNode.createRelationshipTo(childNode,new RelationType(linkType))
    relationship.setProperty("key", key)

    tx.success()

    ok = true
    }
    finally {
      tx.finish()
    }
    ok
  }

  private def saveResource(child: Resource, parentNode: Node, key: String, linkType: String): Boolean = {

    val relationType = new RelationType(linkType)

    var ok = false
    val tx: Transaction = N4JKB().beginTx()
    try {

      val childNode = N4JKB().createNode()
      for ((x, y) <- child.export)
        childNode.setProperty(x, y)
      childNode.setProperty(Constant.KB_CLASS_NAME, child.getClass.getName)
      val relationship = parentNode.createRelationshipTo(childNode, relationType)
      relationship.setProperty("key", key)

      tx.success()
      KBMap.register(child, childNode.getId)
      ok = true
    }
    finally {
      tx.finish()
    }
    ok
  }

  private def loadChild(parent: Node, key: String, linkType: String): Map[String, String] = {

    val relationType = new RelationType(linkType)

    //TODO use кошерный синтаксис
    val i = parent.getRelationships(relationType).iterator()
    while (i.hasNext) //(x:Relationship <- parent.getRelationships.iterator())
     {
      val relationship: Relationship = i.next()
      if (relationship.getProperty("key") == key) {
        val node: Node = relationship.getEndNode
        var values = Map[String, String]()
        val j = node.getPropertyKeys.iterator()
        while (j.hasNext) {
          val key: String = j.next()
          values += key -> node.getProperty(key).toString
        }
        values += (Constant.KB_ID -> node.getId.toString)
        return values
      }
    }
    Nil.toMap[String, String]
  }

  private def loadChildrenList(parent: Node, linkType: String): List[Map[String, String]] = {

    val mapChild = loadChildrenMap(parent, linkType)

    mapChild.values.toList
  }

  private def loadChildrenMap(parent: Node, linkType: String): Map[String, Map[String, String]] = {

    val relationType = new RelationType(linkType)

    var res = new HashMap[String, Map[String, String]]
    //TODO use кошерный синтаксис
    val i = parent.getRelationships(relationType).iterator()
    while (i.hasNext) //(x:Relationship <- parent.getRelationships.iterator()) {
    { val relationship: Relationship = i.next()
      val node: Node = relationship.getEndNode
      if (node.getId != parent.getId) {
        var values = new HashMap[String, String]
        val j = node.getPropertyKeys.iterator()
        while (j.hasNext) {
          val key: String = j.next()
          values += key -> node.getProperty(key).toString
        }
        values += (Constant.KB_ID -> node.getId.toString)
        res += relationship.getProperty("key").toString -> values
      }
    }
    res
  }

  /*
  override def loadSelf(selfID:KBNodeId):Map[String, String] ={
    loadSelf(N4JKB().getNodeById(selfID))
  }

  private def loadSelf(node:Node):Map[String, String] = {
    var values = new HashMap[String,  String]
    val j = node.getPropertyKeys.iterator()
    while(j.hasNext)
    {
      val key:String = j.next()
      values += key -> node.getProperty(key).toString
    }
    values += (Constant.KB_ID -> node.getId.toString)
    values
  }
  */

  //  private var goalIndex:Index[Node] = _GraphDb.index().forNodes( Goal.getClass.getName )

  def init(): Boolean = {
    try {
      N4JKB.apply()
      true
    }
    catch {
      case e: Exception => {
        LoggerFactory.getLogger(this.getClass).error("Cann't create database: {}", e.toString)
      }
      false
    }
  }

  /**
   * Clean database . Use only for testing purpose.
   */
  def clean() {
    if (_GraphDb==null)   N4JKB.apply()
    _GraphDb.shutdown()
    log.debug("Cleaning directory: "+defaultFilename)
    deleteFileOrDirectory(new File(defaultFilename))
    log.debug("Reinit database: "+defaultFilename)
    new File(defaultFilename).mkdirs()
    N4JKB.apply()

  }


 def deleteFileOrDirectory(  file:File ) {
    if ( file.exists() ) {
      if ( file.isDirectory() ) {
        file.listFiles().foreach (f=>{
          deleteFileOrDirectory (f)
        })

      }
      file.delete();
    }
  }

  //def goals = {goalIndex.ensuring(true)}
  //List(Goal("ProcessIncident"), Goal("ClassifyIncident"), Goal("GetMostProbableAction"), Goal("SearchSolution"))

  private def addIndexedNode(resource: Resource, index: Index[Node]): Option[Node] = addIndexedNode(resource, resource.uri.name, index)

  private def addIndexedNode(resource: Resource, key: String, index: Index[Node]): Option[Node] = {
    val tx: Transaction = _GraphDb.beginTx()
    try {
      val node: Node = _GraphDb.createNode()
      node.setProperty("type", resource.getClass.getName)
      node.setProperty("name", resource.uri.name)
      index.add(node, "key", key)
      tx.success()
      return Option(node)
    }
    finally {
      tx.finish()
    }
    None
  }

  def addIndexedNode(key: String): Node = {
    val node: Node = _GraphDb.createNode()
    node.setProperty(keyField, key)
    //TODO: _nodeIndex.add( node, keyField , key );
    node
  }

}

class RelationType(_name: String) extends RelationshipType {
  def name(): String = {
    _name
  }
}

/**
 * A virtual machine shutdown hook.
 *
 * Registered shutdown hooks are executed when the virtual machine
 * terminates normally. For more information read the documentation
 * of Runtime. Constructor creates an unregistered instance
 * @param name the shutdown hook's name (for e.g. logging purposes)
 * @param body code to execute when the shutdown hook is executed
 */
final class ShutdownHook(
                          val name: String,
                          private[this] val body: => Unit) {

  def this(body: => Unit) = this("", body)

  private[this] val hook = new Thread(new Runnable() {
    def run {
      body
    }
  }, name)

  /**Registers this as a virtual machine shutdown hook. */
  def register() = {
    Runtime.getRuntime().addShutdownHook(hook)
    this
  }

  /**Deregisters this from the virtual machine. */
  def deregister() = Runtime.getRuntime.removeShutdownHook(hook)

  override def toString = "ShutdownHook(" + name + ")"
}

/**Factory for ShutdownHook instances. */
final object ShutdownHook {
  /**Creates a registered instance. */
  def apply(name: String, body: => Unit) = new ShutdownHook(name, body).register()

  /**Creates a registered instance. */
  def apply(body: => Unit) = new ShutdownHook("", body).register()
}