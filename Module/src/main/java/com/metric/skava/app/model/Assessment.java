package com.metric.skava.app.model;

import android.net.Uri;

import com.metric.skava.app.data.IdentifiableEntity;
import com.metric.skava.calculator.barton.model.Q_Calculation;
import com.metric.skava.calculator.rmr.model.RMR_Calculation;
import com.metric.skava.discontinuities.model.DiscontinuityFamily;
import com.metric.skava.instructions.model.SupportRecomendation;
import com.metric.skava.rockmass.model.FractureType;

import java.util.Date;
import java.util.List;

/**
 * Created by metricboy on 2/23/14.
 */
public class Assessment implements IdentifiableEntity {

    private String code;
    private String internalCode;
    private TunnelFace face;

    private User geologist;
    private ExcavationSection section;
    private Date date;
    private Double initialPeg;
    private Double finalPeg;
    private Double currentAdvance;
    private Double accummAdvance;

    private ExcavationMethod method;
    private Short orientation;
    private Short slope;

    private FractureType fractureType;
    private Short blockSize;
    private Short numberOfJoints;
    private String outcropDescription;

    private SupportRecomendation recomendation;
    private List<DiscontinuityFamily> discontinuitySystem;
    private List<Uri> pictureUriList;
    private Q_Calculation qCalculation;
    private RMR_Calculation rmrCalculation;

    //These belong to the tunnel and seems to be non editable
//    private ExcavationFactors excavationFactors;

    public Assessment(String internalCode) {
        this.internalCode = internalCode;
//        this.code = UUID.randomUUID().toString();
    }

    public java.lang.String getCode() {
        return code;
    }

    public void setCode(java.lang.String code) {
        this.code = code;
    }

    public String getInternalCode() {
        return internalCode;
    }

    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }

    public ExcavationProject getProject() {
        TunnelFace face = getFace();
        if (face != null) {
            Tunnel tunnel = face.getTunnel();
            if (tunnel != null) {
                ExcavationProject project = tunnel.getProject();
                return project;
            }
        }
        return null;
    }

    public Tunnel getTunnel() {
        TunnelFace face = getFace();
        if (face != null) {
            return face.getTunnel();
        }
        return null;
    }



    public TunnelFace getFace() {
        return face;
    }

    public void setFace(TunnelFace face) {
        this.face = face;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getGeologist() {
        return geologist;
    }

    public void setGeologist(User geologist) {
        this.geologist = geologist;
    }

    public ExcavationMethod getMethod() {
        return method;
    }

    public void setMethod(ExcavationMethod method) {
        this.method = method;
    }

    public List<Uri> getPictureUriList() {
        return pictureUriList;
    }

    public void setPictureUriList(List<Uri> pictureUriList) {
        this.pictureUriList = pictureUriList;
    }

    public Q_Calculation getQCalculation() {
        return qCalculation;
    }

    public void setQCalculation(Q_Calculation qCalculation) {
        this.qCalculation = qCalculation;
    }

    public RMR_Calculation getRmrCalculation() {
        return rmrCalculation;
    }

    public void setRmrCalculation(RMR_Calculation rmrCalculation) {
        this.rmrCalculation = rmrCalculation;
    }

    public ExcavationSection getSection() {
        return section;
    }

    public void setSection(ExcavationSection section) {
        this.section = section;
    }

    public Double getInitialPeg() {
        return initialPeg;
    }

    public void setInitialPeg(Double pk) {
        this.initialPeg = pk;
    }

    public Double getFinalPeg() {
        return finalPeg;
    }

    public void setFinalPeg(Double finalPeg) {
        this.finalPeg = finalPeg;
    }

    public Double getCurrentAdvance() {
        return currentAdvance;
    }

    public void setCurrentAdvance(Double currentAdvance) {
        this.currentAdvance = currentAdvance;
    }

    public Double getAccummAdvance() {
        return accummAdvance;
    }

    public void setAccummAdvance(Double accummAdvance) {
        this.accummAdvance = accummAdvance;
    }

    public Short getOrientation() {
        return orientation;
    }

    public void setOrientation(Short orientation) {
        this.orientation = orientation;
    }

    public Short getSlope() {
        return slope;
    }

    public void setSlope(Short slope) {
        this.slope = slope;
    }

    public FractureType getFractureType() {
        return fractureType;
    }

    public void setFractureType(FractureType fractureType) {
        this.fractureType = fractureType;
    }

    public Short getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(Short blockSize) {
        this.blockSize = blockSize;
    }

    public Short getNumberOfJoints() {
        return numberOfJoints;
    }

    public void setNumberOfJoints(Short numberOfJoints) {
        this.numberOfJoints = numberOfJoints;
    }

    public java.lang.String getOutcropDescription() {
        return outcropDescription;
    }

    public void setOutcropDescription(java.lang.String outcropDescription) {
        this.outcropDescription = outcropDescription;
    }

    public SupportRecomendation getRecomendation() {
        return recomendation;
    }

    public void setRecomendation(SupportRecomendation recomendation) {
        this.recomendation = recomendation;
    }

    public List<DiscontinuityFamily> getDiscontinuitySystem() {
        return discontinuitySystem;
    }

    public void setDiscontinuitySystem(List<DiscontinuityFamily> discontinuitySystem) {
        this.discontinuitySystem = discontinuitySystem;
    }

//    public ExcavationFactors getExcavationFactors() {
//        return excavationFactors;
//    }
//
//    public void setExcavationFactors(ExcavationFactors excavationFactors) {
//        this.excavationFactors = excavationFactors;
//    }


    @Override
    public java.lang.String toString() {
        return "Assessment{" +
                "internalCode = " + internalCode +
                ", project = " + getProject() +
                ", tunnel = " + getTunnel() +
                ", face = " + face +
                '}';
    }


}
