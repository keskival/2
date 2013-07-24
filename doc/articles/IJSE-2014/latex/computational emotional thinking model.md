# Computational emotional thinking model

## Introduction

Emotions are in focus of a lot of researchers from neuro-scientists [], to computer science specialists[], and [emotional intelligence](http://en.wikipedia.org/wiki/Emotional_intelligence) (EQ) becomes important, at least not less than IQ.
There were only some computational emotions models created[computational_emotion, ...] and we failed to find computational emotion thinking model already created.

Marvin Minsky in his book "The emotion machine"[emotion_machine] described human thinking model, and proposed promising base and framework for emotional thinking implementation in computing system. He demonstrated that emotions are inseparable parts of thinking. There are several number of researchers that share this point of view on emotions. Rosalind Picard in her article[challenges] "Challenges of affective programming" states: "Although people are the most intelligent systems we know, and people’s emotion appears to play a vital role in regulating and guiding intelligence, it does not mean there might not be a better way to implement some of these goals in machines. It may be possible that there is something like the wheel, which has no precise human or animal equivalent, but which provides for some of the same locomotion goals. There may exist a kind of alien intelligent living system, something we’ve never encountered, which achieves its intelligence without having anything like emotion."  Hence there is no good example of unemotional intelligence and emotions are inseparable part of thinking we face the problem of emotional thinking model implementation as soon as we attempt to construct more or less comparable to human intelligence. We demonstrate below framework based on synthesis of AI, psychology and neuro-science theories and frameworks of emotions. We propose mapping and influence mechanism of neurotransmitters to computing system management parameters.

## Emotional thinking model

### Model basis

#### Minsky's Thinking model

First basis that is used in this work is  AI. We used Marvin Minsky emotional thinking approach.
Marvin Minsky in his book "The emotion machine"[emotion_machine] described human thinking model. Main structural idea that we used and adopted is "Model of six".

Model of six thinking levels:

1. Instinctive reactions
1. Learned reactions
1. Deliberative
1. Reflective thinking
1. Self-reflective thinking
1. Self-conscious reflections

All thinking processes are developed in levels listed above. We use following assumption: emotions as part of thinking, at least conscious processes take , should fit thinking model. This way all emotional processed should be expressed in terms of thinking model(levels). This could be understood as base of computational emotional thinking approach.

#### Evolutionary psychology

The other perspective is psychology.  We use Plutchik approach[nature_of_emotion] as main psychological model. Plutchik indicated 8 basic emotions grouped in 4 pairs:

1. Joy - sorrow
1. Anger - fear
1. Acceptance - disgust
1. Surprise - expectancy

Emotions are organised in three dimensional circumplex model where third dimension is emotional strength. Basic emotions could be mixed based on color theory, in high-level, more complex emotions. Complex emotions based on two original are called "primary dyads":

1. Love
1. Submission
1. Awe
1. Disapproval
1. Remorse
1. Contempt
1. Aggressiveness
1. Optimism

More complex emotions based on three could be combined in similar way, see Cambria [sentic_computing].
Cambria [hourglass_of_emotions] used Gauss function to describe passage from a sentic level to another. We interpreted it as: Gaussian function regulates influence of subjective human perception of inbound stimulus over objective brain response. Semir Zeki[Neural_Correlates_of_Hate] describes neural activities correlation to emotion(hate) as Gaussian.

![Gaussian 3D](Gauss 3D.png)


#### Neuromodulation and neurotransmission theory

Third basis of emotional thinking model is neuroscience. Objective brain work is described as neuromodulation process in Fellous(1999) and Arbib & Fellous (2004) with base of four neuromodulatory systems:

1. Dopamine
1. Serotonin
1. Opioids

Close to their work is model based on monoamine neurotransmitters called "Cube of emotions" of Hugo Lövheim[Cube_of_emotions].
Author describes emotions and correlation to neurotransmitters concentration. Using only three main neurotransmitters:

1. Nor-adrenaline
1. Dopamine
1. Serotonin

Lövheim uses three dimensional model-cube and assumes that all emotional states could be placed in the three dimensional cube with neurotransmitters as axes and eight basic emotions ordered in ordered in an orthogonal coordinate system that are affective states that are inherited from affect theory of Tomkins (Tomkins (1962); Tomkins (1963); Tomkins (1991); Tomkins (1981)):

 1. Enjoyment/Joy
 1. Interest/Excitement
 1. Surprise
 1. Anger/Rage
 1. Disgust
 1. Distress/Anguish
 1. Fear/Terror
 1. Shame/Humiliation

This group of affects does match the basic Plutchik's emotions except for humiliation that could be interpreted as contempt. Complete mapping description is presented below.

According to Arbib et al(2004) there are four following neuronal systems involved in to emotional processing:

1. Spinal cord
1. Hypothalamus
1. Amygdala
1. Frontal cortex, cingulate cortex

We correspond spinal cord, hypothalamus and amygdala with instinctive layer of Minsky's thinking model. This mapping is done in the assumption that reflexes, drives and instincts could be placed in instinctive reactions layer responsible for most primitive actions that do not involve conscious reactions or more complex processes like learned reactions or deliberations. Cognitions are could be placed in 5 higher layers that corresponds to working memory and all conscious actions in emotional processing/thinking.

### Emotions in six thinking levels

This is attempt to synthesize theory based on three approaches. First of all we described Plutchik's feedback loops[nature_of_emotion] in Minsky's six thinking levels.

![Emotions in model of six thinking levels](six levels of emotions.png)

Figure 1. [Emotions in model of six thinking levels].

1. Inbound stimulus is been processed(transmitted/apprised) via spinal cord, hypothalamus, amygdathis modella and all these neuronal systems take part in neuromodulation.
1. Neuromodulation actually triggers the emotional state of human and all the rest actions are done under the influence of neuromodulatory systems: nor-adrenaline, dopamine, serotonin. Nor-adrenaline, dopamine, serotonin are neurotransmitters.
1. Instinctive behavior is processed on instinctive reactions layer that usually is not involved in conscious actions.
1. Result of behavior actions is effect state that influences the system again as stimulus. This second stimulus is been apprised on instinctive reactions layer and triggers neuromodulation again. Neuromodulation in it's turn switches emotional state second time. This way stimulus cognition actions started in first emotional state, at some point could continue in second emotional state.
1. Stimulus cognition is processed in cingulate cortex, frontal cortex (working memory) that we correspond to rest 5 layers of thinking model. Stimulus cognition actions is done in the emotional state under influence of neuromodulation and hormons. Stimulus cognition could involve deliberation, further reflection, sef-reflection self-conscious processing (higher emotions) and  emotional state switch.
1. Conscious behavior is activated as the result of stimulus cognition.

#### Stimulus appraisal and stimulus cognition

There are two main ways of inbound stimulus processing: appraisal is done on the instinctive reactions level and usually is processed as non-conscious action, cognition could involve all the rest thinking levels and could consist of complex deliberations and reflections.
Cognitions also include self-conscious reflections over complex emotions like love, awe and aggression. For example startle is been apprised on instinctive reactions layer (spinal cord, hypothalamus, amygdala) where non-conscious decision is made and instinctive behaviour is chosen (it could be even reflex). In case of startle this could be run or jump aside. Only after those instinctive actions are performed human could realise what had happen to him (in the state of effect). In contrast to appraisal cognitions could are performed on higher levels could take some significant time and could include complex reasoning and reflections. For example fear could be triggered not only by inbound stimulus but by long time perception during some horror movie or deliberation over some facts regarding the world. In contrast to startle fear triggers complex conscious behaviour that could in it's turn become panic and trigger less intelligent behaviour like shouting and running from side to side. Both instinctive and conscious behaviour produces effect state. Effect is an environmental state that was altered from previous state via behaviour. Running in case of startle places human in safe place that produces effect which influence stimulus event and it's appraisal and as consequence emotional/affective state.

#### Feeling the state and neuromodulation

Feeling the emotional state is closely related to neuromodulation the physiological arousal processes in brain. The result of stimulus appraisal is subjective emotional state one of 8 basic Plutchik emotions that are used as dimensions and strength of emotion. This way subjective emotional state is been expressed via two coordinates: emotional state nature and strength of emotion. Emotional state nature and emotional strength are in range from 0 to 1. Subjective strength of emotion corresponds to objective brain activities via Gaussian function see Cambria et al(2012)[hourglass_of_emotions]. Objective brain activities are expressed via neuromodulation, see Arbib et al(2004) that is expressed in terms of neurotransmitters concentration. Neurotransmitters  concentration is expressed in range from 0 to 1 in cube of emotions, see Lövheim[Cube_of_emotions]. This way inbound stimulus[emotion_machine]lus is been apprised and triggers subjective emotional state switch and then objective brain functions as result of neurotransmitters concentration variation. For example the system is scared. System switched it's state to terror with maximum strength 1. This subjective terror strength is mapped to objective dopamine(neurotransmitter) concentration, in our case this is maximum 1. Under the influence of maximum concentration of dopamine all further actions is been performed: decisions over instinctive behavior, stimulus cognitions, selection of conscious behavior. All higher thinking processes control and influence lower actions. For example: if human is scared during watching the film he/she usually does not jump and run away. Some kind of reflection(reflection thinking level) is been used: "This is just a movie, nothing terrible is going to happen to me". This is done by switching emotional state on reflection thinking level.

#### Neuromodulation to computing system management mapping

All that was presented above was the description of human thinking process and human emotions in terms of it. Only one reference to AI was done to Marvin Minsky "The emotion machine book"[emotion_machine]. Result of neuromodulation is neurotransmitters secretion. Roughly we could state that noradrenaline influence overall speed of thinking process, dopamine and serotonin reward processing and learning.To map neurotransmitters influence to computing system first we created the list of computing system management parameters:

 1. Generic:
   2. CPU power: noradrenaline, dopamine
   2. Memory
     3. Pattern generation: serotonin
     3. Pattern activation: dopamine
     3. Learning: serotonin
   2. Storage: serotonin, dopamine
 1. Decision making/reward processing:
   2. Confidence: serotonin
   2. Satisfaction: serotonin
   2. Motivation, wanting: dopamine
   2. Risky choices inclination: dopamine
   2. Number of option to process: noradrenaline

Parameters are grouped in two folders: most obvious computing system parameters: CPU power management - could be threads distribution or load balancing; memory - is working memory(short term memory) distribution between threads or computing processes, where previously remembered patters are activated under influence of dopamine and generated under influence of serotonin, learning is impacted by serotonin though pattern activation is impacted by dopamine that is the part of learning too; storage - long term memory management is influenced both by serotonin and dopamine, higher concentrations of both neurotransmitters the better action is remembered(less probability to forget). Second group contains parameters that influence decision making in probabilistic reasoning system. This reasoning is done mainly in deliberation and learned reaction layers. Parameters: confidence, satisfaction, risky are used to highlight actions stored(remembered). Confidence of the system in option is directly influenced by serotonin; satisfaction by a reward is influenced by serotonin; motivation is heavily impacted by dopamine; system tends to choose not risky/safe actions under impact of dopamine; number of options in width and depth to be processed is heavily influenced by noradrenaline.

For example: system is in fear state. Dopamine impacts system at half strength. This makes system choose actions highlighted with high rewards(safest in case of fear). High noradrenaline in rage state cause system to think as quick as possible taking in account as less as possible options implementing first action(usually not really safe) selected "fight or flight" reaction.

It's worth to note that duration of each neurotransmitter impact is highly individual and requires further development from computing system development perspective. Currently we suppose that each neurotransmitter is terminated right after it impacts the system.

#### Conformity with Picard criteria

Rosalind Picard in her article "What does it mean for a computer to "have" emotions?"[what] suggested following criteria to measure emotional capabilities of a computing system:

1. Emotional appearance
1. Multi-level emotion generation
1. Emotional experience
1. Mind-body interactions

Emotional appearance is not discussed in this work. Multi-level emotion cognition and behavior generation was demonstrated in "Emotions in six thinking levels" and "Feeling the state and neuromodulation" chapters. Emotional experience is complex that consists of:  "cognitive or semantic label; physiological changes; subjective feeling, intuition" we have scratch the surface and suggested psychological changes mechanism according to Plutchik model and subjective emotions perceptions in this work. Mind-body interactions consists of: "conscious and non-conscious events; regulatory and signaling mechanisms; biasing mechanisms, intuition; physiological and biochemical changes; sentic modulation, lying impacts pressure; waveform of love; smiles induce joy..." this partly could be implemented by presented model. Conscious and non-conscious mechanisms of stimulus processing was presented in "Stimulus appraisal and stimulus cognition" section. Psychological and biochemical changes where presented in "Feeling the state and neuromodulation" and "Neuromodulation to computing system management mapping".

We propose this approach as framework for emotional thinking processes. This could be starting point of further research and development of emotional thinking. From our perspective this kind of approach could be widely used in following domains:

 1. Intellectual assistant capable of emotional dialogs and thinking
 1. Help desk automation
 1. Call centre automation
 1. Software and hardware support automation
 1. Virtual friends
 1. Nursing software
 1. Applications in emotional robots

#### Higher emotions

Plutchik[nature_of_emotion] suggested "primary dyads", see "Evolutionary psychology" section above, could be produced as mixture of primary colors of basic emotions. For example: joy(yellow) plus trust(green) produces love(lemon), anger(red) plus disgust(lilac) produces contempt(violet). Higher or complex emotions requires higher levels of thinking and self-awareness to be involved, this way they are mainly triggered on self-conscious and self-reflective thinking levels. Triads could be produced in similar way to dyads: joy + trust + anger = jealousy, see [sentic_computing].

## Conclusion

Emotions are part of human thinking. Role of emotions could not be underestimated and they could not be ignored in human thinking modelling.
There are three bases of presented computational emotions model. First AI, Marvin Minsky "The emotion machine"[emotion_machine] thinking model(model of six). We presented emotion processing workflow . Evolutionary psychological model: "wheel of emotions" by Plutchik[nature_of_emotion]. Plutchik proposed 8 basic emotions grouped in pairs:joy - sorrow, anger - fear, acceptance - disgust, surprise - expectancy. We used "wheel of emotions" as subjective emotional state model. Objective emotional brain activity is modeled by "cube of emotions" the monoamine neurotransmitters emotions model. We present following parameters of computing system management with mapping to monoamine neurotransmitters
 that impact this parameter.

 1. Generic:
   2. CPU power: noradrenaline, dopamine
   2. Memory
     3. Pattern generation: serotonin
     3. Pattern activation: dopamine
     3. Learning: serotonin
   2. Storage: serotonin, dopamine
 1. Decision making/reward processing:
   2. Confidence: serotonin
   2. Satisfaction: serotonin
   2. Motivation, wanting: dopamine
   2. Risky choices inclination: dopamine
   2. Number of option to process: noradrenaline

This could be base for further implementation as emotional thinking process used as base framework for general AI applications.

## References

Author, A. A. (2005). Title of work. Location/City, State: Publisher.

Arbib, M.A., & Fellous J. M. (2004). Emotions: from brain to robot. Trends in Cognitive Sciences, 8(12), 554-559
Fellous, J.M. (1999) Neuromodulatory basis of emotion. The Neuro-scientist 5, 283–294
Lövheim, H (),
Tomkins, S. (1962). Affect imagery consciousness volume I the positive affects. New York: Springer Publishing Company.
Tomkins, S. (1963). Affect imagery consciousness volume II the negative affects. New York: Springer Publishing Company.
Tomkins, S. (1991). Affect imagery consciousness volume III the negative affects anger and fear. New York: Springer Publishing Company.
Tomkins, S. (1981). The quest for primary motives: biography and autobiography of an idea. J Pers Soc Psychol, 41, 306–335.