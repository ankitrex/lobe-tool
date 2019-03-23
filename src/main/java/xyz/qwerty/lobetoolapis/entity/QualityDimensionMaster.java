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

@Entity(name="quality_dimension_master")
public class QualityDimensionMaster implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    
    @Column(name="dimension_name", nullable=false, length=50)
    private String dimensionName;
    
    @OneToMany(mappedBy="qualityDimensionMaster")
    private Set<RubrikQualityDimensions> rubrikQualityDimensions;

    /** Default constructor. */
    public QualityDimensionMaster() {
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
     * Access method for dimensionName.
     *
     * @return the current value of dimensionName
     */
    public String getDimensionName() {
        return dimensionName;
    }

    /**
     * Setter method for dimensionName.
     *
     * @param aDimensionName the new value for dimensionName
     */
    public void setDimensionName(String aDimensionName) {
        dimensionName = aDimensionName;
    }

    /**
     * Access method for rubrikQualityDimensions.
     *
     * @return the current value of rubrikQualityDimensions
     */
    public Set<RubrikQualityDimensions> getRubrikQualityDimensions() {
        return rubrikQualityDimensions;
    }

    /**
     * Setter method for rubrikQualityDimensions.
     *
     * @param aRubrikQualityDimensions the new value for rubrikQualityDimensions
     */
    public void setRubrikQualityDimensions(Set<RubrikQualityDimensions> aRubrikQualityDimensions) {
        rubrikQualityDimensions = aRubrikQualityDimensions;
    }

    /**
     * Compares the key for this instance with another QualityDimensionMaster.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class QualityDimensionMaster and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof QualityDimensionMaster)) {
            return false;
        }
        QualityDimensionMaster that = (QualityDimensionMaster) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another QualityDimensionMaster.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof QualityDimensionMaster)) return false;
        return this.equalKeys(other) && ((QualityDimensionMaster)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[QualityDimensionMaster |");
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
