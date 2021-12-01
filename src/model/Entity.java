package model;

import redactor.StringRedactor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Entity implements Comparable<Entity> {
    private Integer fid;
    private Integer serialNumber;
    private String memberCode;
    private Integer acctType;
    private LocalDate openedDate;
    private Integer acctRteCde;
    private LocalDate reportingDate;
    private Integer creditLimit;

    public Entity() {
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCpde) {
        this.memberCode = memberCpde;
    }

    public Integer getAcctType() {
        return acctType;
    }

    public void setAcctType(Integer acctType) {
        this.acctType = acctType;
    }

    public LocalDate getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(LocalDate openedDate) {
        this.openedDate = openedDate;
    }

    public Integer getAcctRteCde() {
        return acctRteCde;
    }

    public void setAcctRteCde(Integer acctRteCde) {
        this.acctRteCde = acctRteCde;
    }

    public LocalDate getReportingDate() {
        return reportingDate;
    }

    public void setReportingDate(LocalDate reportingDate) {
        this.reportingDate = reportingDate;
    }

    public Integer getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Integer creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String result = fid + ";" +
                serialNumber + ";" + memberCode + ";" +
                acctType + ";" + openedDate.format(formatter) + ";" +
                acctRteCde  + ";" + reportingDate.format(formatter)  + ";"  + creditLimit;
    return StringRedactor.addQuoteTiString(result);
    }

    @Override
    public int compareTo(Entity o) {
        return this.fid - o.fid;
    }


//FID;SERIAL_NUM;MEMBER_CODE;ACCT_TYPE;OPENED_DT;ACCT_RTE_CDE;REPORTING_DT;CREDIT_LIMIT
    //
    //2000;2202099;4B01GG000001;9;04.06.2014;0;14.10.2014;100000
}
