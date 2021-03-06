/*
 * opencog/atomspace/IndefiniteTruthValue.h
 *
 * Copyright (C) 2002-2007 Novamente LLC
 * All Rights Reserved
 *
 * Written by Welter Silva <welter@vettalabs.com>
 *            Fabricio Silva <fabricio@vettalabs.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License v3 as
 * published by the Free Software Foundation and including the exceptions
 * at http://opencog.org/wiki/Licenses
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, write to:
 * Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

#ifndef _OPENCOG_INDEFINITE_TRUTH_VALUE_H
#define _OPENCOG_INDEFINITE_TRUTH_VALUE_H

#include <vector>

#include <opencog/atomspace/TruthValue.h>
#include <boost/shared_ptr.hpp>

namespace opencog
{

/*
 * Indefinite probabilities are in the form ([L,U],b,N). In practical work,
 * N will be hold constant and thus we have only ([L,U],b).
 */
class IndefiniteTruthValue : public TruthValue
{

private:

    strength_t U;
    strength_t L;
    confidence_t confidenceLevel; //referred as "b" in the paper
    bool symmetric;

    // used in inference rule procedure in order to compute L1 and U1
    // when diff is negative it means that it is outdated and must be
    // recalculated
    strength_t diff;

    // below, "mean", "count" and "confidence" are all three attributes
    // used to translate an indefinite TV into a simple TV.
    // If "mean" (as well as "count" or "confidence") is negative
    // it means that it outdated and therefore must be recalculted,
    // otherwise, i.e. positive of null, it means that the current value
    // is correct
    mutable strength_t mean;
    mutable count_t count;
    mutable confidence_t confidence;

    std::vector<strength_t*> firstOrderDistribution;

    void init(strength_t l = 0.0f, strength_t u = 0.0f,
              confidence_t c = DEFAULT_CONFIDENCE_LEVEL);
    void copy(const IndefiniteTruthValue&);

    //find diff by dichotomy
    strength_t findDiff(strength_t idiff);

public:
    IndefiniteTruthValue();
    IndefiniteTruthValue(strength_t l, strength_t u,
                         confidence_t c = DEFAULT_CONFIDENCE_LEVEL);
    IndefiniteTruthValue(IndefiniteTruthValue const&);

    IndefiniteTruthValue* clone() const;
    IndefiniteTruthValue& operator=(const TruthValue& rhs) throw (RuntimeException);

    //it is a strict equality comparison, without error interval tolerance
    virtual bool operator==(const TruthValue& rhs) const;

    static IndefiniteTruthValue* fromString(const char*);

    strength_t getMean() const;
    strength_t getU() const;
    strength_t getL() const;
    confidence_t getConfidenceLevel() const;
    strength_t getDiff();
    const std::vector<strength_t*>& getFirstOrderDistribution() const;

    void setMean(strength_t);
    void setU(strength_t);
    void setL(strength_t);
    void setConfidenceLevel(confidence_t);
    void setDiff(strength_t);
    void setFirstOrderDistribution(const std::vector<strength_t*>&);

    count_t getCount() const;
    confidence_t getConfidence() const;
    strength_t getU_() const;
    strength_t getL_() const;
    bool isSymmetric() const;

    float toFloat() const;
    std::string toString() const;
    TruthValueType getType() const;

    static confidence_t DEFAULT_CONFIDENCE_LEVEL;
    static count_t DEFAULT_K;
    static strength_t diffError;
    static strength_t s; //Nil : not that sure s should be strength_t
    static void setDefaultConfidenceLevel(confidence_t c) {
        DEFAULT_CONFIDENCE_LEVEL = c;
    }
    static void setDefaultK(count_t k) {
        DEFAULT_K = k;
    }
};

typedef boost::shared_ptr<IndefiniteTruthValue> IndefiniteTruthValuePtr;

} // namespace opencog

#endif // _OPENCOG_INDEFINITE_TRUTH_VALUE_H
