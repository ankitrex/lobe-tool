// Generated with g9.

package xyz.qwerty.lobetoolapis.entity;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import lombok.Data;

@Entity(name="question_master")
public class QuestionMaster implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    
    @Column(nullable=false, length=100)
    private String question;
    
    @Column(name="question_meta", nullable=false, length=1000)
    private String questionMeta;
    
    @Column(name="score_0", nullable=false, length=1000)
    private String score0;
    
    @Column(name="score_0_images", nullable=false, length=1000)
    private String score0Images;
    
    @Column(name="score_1", nullable=false, length=1000)
    private String score1;
    
    @Column(name="score_1_images", nullable=false, length=1000)
    private String score1Images;
    
    @Column(name="score_2", nullable=false, length=1000)
    private String score2;
    
    @Column(name="score_2_images", nullable=false, length=1000)
    private String score2Images;
    
    @Column(name="score_3", nullable=false, length=1000)
    private String score3;
    
    @Column(name="score_3_images", nullable=false, length=1000)
    private String score3Images;
    
    @Column(name="rubrik_type", nullable=false, length=20)
    private String rubrikType;
    
    @Column(name="quality_dimension", nullable=false, length=50)
    private String qualityDimension;
    
    @Column(nullable=false, length=3)
    private boolean optional;
    
    @OneToMany(mappedBy="questionMaster")
    private Set<LobeScores> lobeScores;
    
    @OneToMany(mappedBy="questionMaster")
    private Set<RubrikQuestions> rubrikQuestions;

    /** Default constructor. */
    public QuestionMaster() {
        super();
    }

    /**
     * Access method for id.
     *
     * @return the current value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for id.
     *
     * @param aId the new value for id
     */
    public void setId(int aId) {
        id = aId;
    }

    /**
     * Access method for question.
     *
     * @return the current value of question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Setter method for question.
     *
     * @param aQuestion the new value for question
     */
    public void setQuestion(String aQuestion) {
        question = aQuestion;
    }

    /**
     * Access method for questionMeta.
     *
     * @return the current value of questionMeta
     */
    public String getQuestionMeta() {
        return questionMeta;
    }

    /**
     * Setter method for questionMeta.
     *
     * @param aQuestionMeta the new value for questionMeta
     */
    public void setQuestionMeta(String aQuestionMeta) {
        questionMeta = aQuestionMeta;
    }

    /**
     * Access method for score0.
     *
     * @return the current value of score0
     */
    public String getScore0() {
        return score0;
    }

    /**
     * Setter method for score0.
     *
     * @param aScore0 the new value for score0
     */
    public void setScore0(String aScore0) {
        score0 = aScore0;
    }

    /**
     * Access method for score0Images.
     *
     * @return the current value of score0Images
     */
    public String getScore0Images() {
        return score0Images;
    }

    /**
     * Setter method for score0Images.
     *
     * @param aScore0Images the new value for score0Images
     */
    public void setScore0Images(String aScore0Images) {
        score0Images = aScore0Images;
    }

    /**
     * Access method for score1.
     *
     * @return the current value of score1
     */
    public String getScore1() {
        return score1;
    }

    /**
     * Setter method for score1.
     *
     * @param aScore1 the new value for score1
     */
    public void setScore1(String aScore1) {
        score1 = aScore1;
    }

    /**
     * Access method for score1Images.
     *
     * @return the current value of score1Images
     */
    public String getScore1Images() {
        return score1Images;
    }

    /**
     * Setter method for score1Images.
     *
     * @param aScore1Images the new value for score1Images
     */
    public void setScore1Images(String aScore1Images) {
        score1Images = aScore1Images;
    }

    /**
     * Access method for score2.
     *
     * @return the current value of score2
     */
    public String getScore2() {
        return score2;
    }

    /**
     * Setter method for score2.
     *
     * @param aScore2 the new value for score2
     */
    public void setScore2(String aScore2) {
        score2 = aScore2;
    }

    /**
     * Access method for score2Images.
     *
     * @return the current value of score2Images
     */
    public String getScore2Images() {
        return score2Images;
    }

    /**
     * Setter method for score2Images.
     *
     * @param aScore2Images the new value for score2Images
     */
    public void setScore2Images(String aScore2Images) {
        score2Images = aScore2Images;
    }

    /**
     * Access method for score3.
     *
     * @return the current value of score3
     */
    public String getScore3() {
        return score3;
    }

    /**
     * Setter method for score3.
     *
     * @param aScore3 the new value for score3
     */
    public void setScore3(String aScore3) {
        score3 = aScore3;
    }

    /**
     * Access method for score3Images.
     *
     * @return the current value of score3Images
     */
    public String getScore3Images() {
        return score3Images;
    }

    /**
     * Setter method for score3Images.
     *
     * @param aScore3Images the new value for score3Images
     */
    public void setScore3Images(String aScore3Images) {
        score3Images = aScore3Images;
    }

    /**
     * Access method for rubrikType.
     *
     * @return the current value of rubrikType
     */
    public String getRubrikType() {
        return rubrikType;
    }

    /**
     * Setter method for rubrikType.
     *
     * @param aRubrikType the new value for rubrikType
     */
    public void setRubrikType(String aRubrikType) {
        rubrikType = aRubrikType;
    }

    /**
     * Access method for qualityDimension.
     *
     * @return the current value of qualityDimension
     */
    public String getQualityDimension() {
        return qualityDimension;
    }

    /**
     * Setter method for qualityDimension.
     *
     * @param aQualityDimension the new value for qualityDimension
     */
    public void setQualityDimension(String aQualityDimension) {
        qualityDimension = aQualityDimension;
    }

    /**
     * Access method for optional.
     *
     * @return true if and only if optional is currently true
     */
    public boolean getOptional() {
        return optional;
    }

    /**
     * Setter method for optional.
     *
     * @param aOptional the new value for optional
     */
    public void setOptional(boolean aOptional) {
        optional = aOptional;
    }

    /**
     * Access method for lobeScores.
     *
     * @return the current value of lobeScores
     */
    public Set<LobeScores> getLobeScores() {
        return lobeScores;
    }

    /**
     * Setter method for lobeScores.
     *
     * @param aLobeScores the new value for lobeScores
     */
    public void setLobeScores(Set<LobeScores> aLobeScores) {
        lobeScores = aLobeScores;
    }

    /**
     * Access method for rubrikQuestions.
     *
     * @return the current value of rubrikQuestions
     */
    public Set<RubrikQuestions> getRubrikQuestions() {
        return rubrikQuestions;
    }

    /**
     * Setter method for rubrikQuestions.
     *
     * @param aRubrikQuestions the new value for rubrikQuestions
     */
    public void setRubrikQuestions(Set<RubrikQuestions> aRubrikQuestions) {
        rubrikQuestions = aRubrikQuestions;
    }

    /**
     * Compares the key for this instance with another QuestionMaster.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class QuestionMaster and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof QuestionMaster)) {
            return false;
        }
        QuestionMaster that = (QuestionMaster) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another QuestionMaster.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof QuestionMaster)) return false;
        return this.equalKeys(other) && ((QuestionMaster)other).equalKeys(this);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        int i;
        int result = 17;
        i = getId();
        result = 37*result + i;
        return result;
    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[QuestionMaster |");
        sb.append(" id=").append(getId());
        sb.append("]");
        return sb.toString();
    }

    /**
     * Return all elements of the primary key.
     *
     * @return Map of key names to values
     */
    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<String, Object>(6);
        ret.put("id", Integer.valueOf(getId()));
        return ret;
    }

}
