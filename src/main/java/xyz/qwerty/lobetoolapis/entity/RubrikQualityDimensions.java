// Generated with g9.

package xyz.qwerty.lobetoolapis.entity;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import lombok.Data;

@Entity(name="rubrik_quality_dimensions")
public class RubrikQualityDimensions implements Serializable {

    @Id
    @ManyToOne(optional=false)
    @JoinColumn(name="rubrik_id", nullable=false)
    private Rubrik rubrik;
    
    @Id
    @ManyToOne(optional=false)
    @JoinColumn(name="quality_dimension_id", nullable=false)
    private QualityDimensionMaster qualityDimensionMaster;

    /** Default constructor. */
    public RubrikQualityDimensions() {
        super();
    }

    /**
     * Access method for rubrik.
     *
     * @return the current value of rubrik
     */
    public Rubrik getRubrik() {
        return rubrik;
    }

    /**
     * Setter method for rubrik.
     *
     * @param aRubrik the new value for rubrik
     */
    public void setRubrik(Rubrik aRubrik) {
        rubrik = aRubrik;
    }

    /**
     * Access method for qualityDimensionMaster.
     *
     * @return the current value of qualityDimensionMaster
     */
    public QualityDimensionMaster getQualityDimensionMaster() {
        return qualityDimensionMaster;
    }

    /**
     * Setter method for qualityDimensionMaster.
     *
     * @param aQualityDimensionMaster the new value for qualityDimensionMaster
     */
    public void setQualityDimensionMaster(QualityDimensionMaster aQualityDimensionMaster) {
        qualityDimensionMaster = aQualityDimensionMaster;
    }

    /** Temporary value holder for group key fragment rubrikId */
    private transient int tempRubrikId;

    /**
     * Gets the key fragment id for member rubrik.
     * If this.rubrik is null, a temporary stored value for the key
     * fragment will be returned. The temporary value is set by setRubrikId.
     * This behavior is required by some persistence libraries to allow
     * fetching of objects in arbitrary order.
     *
     * @return Current (or temporary) value of the key fragment
     * @see Rubrik
     */
    public int getRubrikId() {
        return (getRubrik() == null ? tempRubrikId : getRubrik().getId());
    }

    /**
     * Sets the key fragment id from member rubrik.
     * If this.rubrik is null, the passed value will be temporary
     * stored, and returned by subsequent calls to getRubrikId.
     * This behaviour is required by some persistence libraries to allow
     * fetching of objects in arbitrary order.
     *
     * @param aId New value for the key fragment
     * @see Rubrik
     */
    public void setRubrikId(int aId) {
        if (getRubrik() == null) {
            tempRubrikId = aId;
        } else {
            getRubrik().setId(aId);
        }
    }

    /** Temporary value holder for group key fragment qualityDimensionMasterId */
    private transient int tempQualityDimensionMasterId;

    /**
     * Gets the key fragment id for member qualityDimensionMaster.
     * If this.qualityDimensionMaster is null, a temporary stored value for the key
     * fragment will be returned. The temporary value is set by setQualityDimensionMasterId.
     * This behavior is required by some persistence libraries to allow
     * fetching of objects in arbitrary order.
     *
     * @return Current (or temporary) value of the key fragment
     * @see QualityDimensionMaster
     */
    public int getQualityDimensionMasterId() {
        return (getQualityDimensionMaster() == null ? tempQualityDimensionMasterId : getQualityDimensionMaster().getId());
    }

    /**
     * Sets the key fragment id from member qualityDimensionMaster.
     * If this.qualityDimensionMaster is null, the passed value will be temporary
     * stored, and returned by subsequent calls to getQualityDimensionMasterId.
     * This behaviour is required by some persistence libraries to allow
     * fetching of objects in arbitrary order.
     *
     * @param aId New value for the key fragment
     * @see QualityDimensionMaster
     */
    public void setQualityDimensionMasterId(int aId) {
        if (getQualityDimensionMaster() == null) {
            tempQualityDimensionMasterId = aId;
        } else {
            getQualityDimensionMaster().setId(aId);
        }
    }

    /**
     * Compares the key for this instance with another RubrikQualityDimensions.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class RubrikQualityDimensions and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof RubrikQualityDimensions)) {
            return false;
        }
        RubrikQualityDimensions that = (RubrikQualityDimensions) other;
        if (this.getRubrikId() != that.getRubrikId()) {
            return false;
        }
        if (this.getQualityDimensionMasterId() != that.getQualityDimensionMasterId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another RubrikQualityDimensions.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof RubrikQualityDimensions)) return false;
        return this.equalKeys(other) && ((RubrikQualityDimensions)other).equalKeys(this);
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
        i = getRubrikId();
        result = 37*result + i;
        i = getQualityDimensionMasterId();
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
        StringBuffer sb = new StringBuffer("[RubrikQualityDimensions |");
        sb.append(" rubrikId=").append(getRubrikId());
        sb.append(" qualityDimensionMasterId=").append(getQualityDimensionMasterId());
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
        ret.put("rubrikId", Integer.valueOf(getRubrikId()));
        ret.put("qualityDimensionMasterId", Integer.valueOf(getQualityDimensionMasterId()));
        return ret;
    }

}
