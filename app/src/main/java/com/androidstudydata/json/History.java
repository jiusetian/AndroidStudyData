package com.androidstudydata.json;

/**
 * Author：Alex
 * Date：2019/6/29
 * Note：历史信息记录
 */
public class History {
    String id;
    String moduleId;
    String createDateTime;
    String posslnum;
    double latitude;
    double longitude;
    float speed;
    String cog;
    String pdop;
    String hdop;
    private float height;
    private float voltage;

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getVoltage() {
        return voltage;
    }

    public void setVoltage(float voltage) {
        this.voltage = voltage;
    }

    @Override
    public String toString() {
        return "HistoryData{" +
                "id='" + id + '\'' +
                ", moduleId='" + moduleId + '\'' +
                ", createDateTime='" + createDateTime + '\'' +
                ", posslnum='" + posslnum + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", speed=" + speed +
                ", cog='" + cog + '\'' +
                ", pdop='" + pdop + '\'' +
                ", hdop='" + hdop + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getPosslnum() {
        return posslnum;
    }

    public void setPosslnum(String posslnum) {
        this.posslnum = posslnum;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getCog() {
        return cog;
    }

    public void setCog(String cog) {
        this.cog = cog;
    }

    public String getPdop() {
        return pdop;
    }

    public void setPdop(String pdop) {
        this.pdop = pdop;
    }

    public String getHdop() {
        return hdop;
    }

    public void setHdop(String hdop) {
        this.hdop = hdop;
    }
}
