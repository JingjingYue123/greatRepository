package com.djcps.crm.integral.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigInteger;

/**
 * Created by TruthBean on 2017-08-10 16:11.
 */
public class ChangeUserIntegralRequest {
    /**
     * 客户ID
     */
    @NotBlank
    @Length(min = 32, max = 36)
    private String fuserid;

    private String fpartnerid;

    private BigInteger fintegeral;

    @Min(5)
    @Max(10)
    private int fintegeraljournaltype;

    private int fintegeralruletype;

    @Min(1000)
    @Max(9999)
    private int fkeyarea;

    private int fpartnerFkeyarea;

    private String fcreater;

    @Length(max = 20)
    private String fremark;

    public String getFuserid() {
        return fuserid;
    }

    public void setFuserid(String fuserid) {
        this.fuserid = fuserid;
    }

    public String getFpartnerid() {
        return fpartnerid;
    }

    public void setFpartnerid(String fpartnerid) {
        this.fpartnerid = fpartnerid;
    }

    public BigInteger getFintegeral() {
        return fintegeral;
    }

    public void setFintegeral(BigInteger fintegeral) {
        this.fintegeral = fintegeral;
    }

    public int getFintegeraljournaltype() {
        return fintegeraljournaltype;
    }

    public void setFintegeraljournaltype(int fintegeraljournaltype) {
        this.fintegeraljournaltype = fintegeraljournaltype;
    }

    public int getFintegeralruletype() {
        return fintegeralruletype;
    }

    public void setFintegeralruletype(int fintegeralruletype) {
        this.fintegeralruletype = fintegeralruletype;
    }

    public int getFkeyarea() {
        return fkeyarea;
    }

    public void setFkeyarea(int fkeyarea) {
        this.fkeyarea = fkeyarea;
    }

    public int getFpartnerFkeyarea() {
        return fpartnerFkeyarea;
    }

    public void setFpartnerFkeyarea(int fpartnerFkeyarea) {
        this.fpartnerFkeyarea = fpartnerFkeyarea;
    }

    public String getFcreater() {
        return fcreater;
    }

    public void setFcreater(String fcreater) {
        this.fcreater = fcreater;
    }

    public String getFremark() {
        return fremark;
    }

    public void setFremark(String fremark) {
        this.fremark = fremark;
    }

    @Override
    public String toString() {
        return "{" + "\"fuserid\":\'" + fuserid + '\'' + "," + "\"fpartnerid\":" + fpartnerid + "," + "\"fintegeral\":" + fintegeral + "," + "\"fintegeraljournaltype\":" + fintegeraljournaltype + "," + "\"fintegeralruletype\":" + fintegeralruletype + "," + "\"fkeyarea\":" + fkeyarea + "," + "\"fpartnerFkeyarea\":" + fpartnerFkeyarea + "," + "\"fcreater\":\'" + fcreater + '\'' + "," + "\"fremark\":\'" + fremark + '\'' + '}';
    }
}
