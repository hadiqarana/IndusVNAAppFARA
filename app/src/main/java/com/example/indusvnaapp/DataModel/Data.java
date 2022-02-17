package com.example.indusvnaapp.DataModel;

import com.google.gson.annotations.SerializedName;

/*public class Data {
    @SerializedName("id")
    private String id;
    @SerializedName("Head_shape")
    private String Head_shape;
    @SerializedName("Head_size")
    private String Head_size;
    @SerializedName("Swelling")
    private String Swelling;
    @SerializedName("Eyes_right")
    private String Eyes_right;
    @SerializedName("Eyes_left")
    private String Eyes_left;
    @SerializedName("Ears_right")
    private String Ears_right;
    @SerializedName("Ears_left")
    private String Ears_left;
    @SerializedName("Nose")
    private String Nose;
    @SerializedName("Mouth_lips")
    private  String Mouth_lips;
    @SerializedName("Mouth_chain")
    private  String Mouth_chin;
    @SerializedName("facial_appearance")
    private String facial_appearance;
    @SerializedName("Right_Upper_limb")
    private String Right_Upper_limb;
    @SerializedName("Chest")
    private String Left_upper_limb;
    @SerializedName("Right_hand")
    private String Right_hand;
    @SerializedName("Abdomen")
    private  String Abdomen;
    @SerializedName("Umbilicus")
    private String Umbilicus;
    @SerializedName("Penis")
    private String Penis;
    @SerializedName("Female_Genitalia")
    private String Female_Genitalia;
    @SerializedName("Legs_position")
    private String Legs_position;
    @SerializedName("Chest")
    private String  Right_lower_limb;
    @SerializedName("Left_lower_limb")
    private String Left_lower_limb;
    @SerializedName("Right_foot")
    private String Right_foot;
    @SerializedName("Left_foot")
    private String Left_foot;
    @SerializedName("Back")
    private  String Back;
    @SerializedName("Color")
    private String Color;

    public String getScrotum() {
        return Scrotum;
    }

    public void setScrotum(String scrotum) {
        Scrotum = scrotum;
    }

    private String Scrotum;

    public String getAge_days() {
        return Age_days;
    }

    public void setAge_days(String age_days) {
        Age_days = age_days;
    }

    private String Age_days;

    public String getEdit_by() {
        return Edit_by;
    }

    public void setEdit_by(String edit_by) {
        Edit_by = edit_by;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    private String Edit_by;
    private  String Time;

    public Data(String id, String head_shape, String head_size, String swelling, String eyes_right, String eyes_left, String ears_right, String ears_left, String nose, String mouth_lips, String mouth_chin, String facial_apeearance, String features_relatedTo, String neck, String chest, String right_Uper_limb, String left_upper_limb, String right_hand, String left_hand, String abdomen, String umbilicus, String penis, String female_Genitalia, String legs_position, String right_lower_limb, String left_lower_limb, String right_foot, String left_foot, String back, String color, String MRnumber, String ges_age, String headSizeCm, String gender, String length, String wt, String HR, String RR, String temp,String Edit_by,String Time,String age_days,String Scrotum) {
        this.id = id;
        Head_shape = head_shape;
        Head_size = head_size;
        Swelling = swelling;
        Eyes_right = eyes_right;
        Eyes_left = eyes_left;
        Ears_right = ears_right;
        Ears_left = ears_left;
        Nose = nose;
        Mouth_lips = mouth_lips;
        Mouth_chin = mouth_chin;
        this.facial_apeearance = facial_apeearance;
        Features_relatedTo = features_relatedTo;
        Neck = neck;
        Chest = chest;
        Right_Uper_limb = right_Uper_limb;
        Left_upper_limb = left_upper_limb;
        Right_hand = right_hand;
        Left_hand = left_hand;
        Abdomen = abdomen;
        Umbilicus = umbilicus;
        Penis = penis;
        Female_Genitalia = female_Genitalia;
        Legs_position = legs_position;
        Right_lower_limb = right_lower_limb;
        Left_lower_limb = left_lower_limb;
        Right_foot = right_foot;
        Left_foot = left_foot;
        Back = back;
        Color = color;
        this.MRnumber = MRnumber;
        Ges_age = ges_age;
        HeadSizeCm = headSizeCm;
        Gender = gender;
        Length = length;
        Wt = wt;
        this.HR = HR;
        this.RR = RR;
        Temp = temp;
        this.Edit_by=Edit_by;
        this.Time=Time;
        this.Age_days=age_days;
        this.Scrotum=Scrotum;
    }

    public String getMRnumber() {
        return MRnumber;
    }

    public void setMRnumber(String MRnumber) {
        this.MRnumber = MRnumber;
    }

    public String getGes_age() {
        return Ges_age;
    }

    public void setGes_age(String ges_age) {
        Ges_age = ges_age;
    }

    public String getHeadSizeCm() {
        return HeadSizeCm;
    }

    public void setHeadSizeCm(String headSizeCm) {
        HeadSizeCm = headSizeCm;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getLength() {
        return Length;
    }

    public void setLength(String length) {
        Length = length;
    }

    public String getWt() {
        return Wt;
    }

    public void setWt(String wt) {
        Wt = wt;
    }

    public String getHR() {
        return HR;
    }

    public void setHR(String HR) {
        this.HR = HR;
    }

    public String getRR() {
        return RR;
    }

    public void setRR(String RR) {
        this.RR = RR;
    }

    public String getTemp() {
        return Temp;
    }

    public void setTemp(String temp) {
        Temp = temp;
    }

    private String MRnumber;
    private String Ges_age;
    private String HeadSizeCm;
    private String Gender;
    private String Length;
    private String Wt;
    private String HR;
    private String RR;
    private String Temp;
    public Data(String id, String head_shape, String head_size, String swelling, String eyes_right, String eyes_left, String ears_right, String ears_left, String nose, String mouth_lips, String mouth_chin, String facial_apeearance, String features_relatedTo, String neck, String chest, String right_Uper_limb, String left_upper_limb, String right_hand, String left_hand, String abdomen, String umbilicus, String penis, String female_Genitalia, String legs_position, String right_lower_limb, String left_lower_limb, String right_foot, String left_foot, String back, String color) {
        this.id = id;
        Head_shape = head_shape;
        Head_size = head_size;
        Swelling = swelling;
        Eyes_right = eyes_right;
        Eyes_left = eyes_left;
        Ears_right = ears_right;
        Ears_left = ears_left;
        Nose = nose;
        Mouth_lips = mouth_lips;
        Mouth_chin = mouth_chin;
        this.facial_apeearance = facial_apeearance;
        Features_relatedTo = features_relatedTo;
        Neck = neck;
        Chest = chest;
        Right_Uper_limb = right_Uper_limb;
        Left_upper_limb = left_upper_limb;
        Right_hand = right_hand;
        Left_hand = left_hand;
        Abdomen = abdomen;
        Umbilicus = umbilicus;
        Penis = penis;
        Female_Genitalia = female_Genitalia;
        Legs_position = legs_position;
        Right_lower_limb = right_lower_limb;
        Left_lower_limb = left_lower_limb;
        Right_foot = right_foot;
        Left_foot = left_foot;
        Back = back;
        Color = color;

    }

    public Data() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getHead_shape() {
        return Head_shape;
    }

    public void setHead_shape(String head_shape) {
        Head_shape = head_shape;
    }

    public String getHead_size() {
        return Head_size;
    }

    public void setHead_size(String head_size) {
        Head_size = head_size;
    }

    public String getSwelling() {
        return Swelling;
    }

    public void setSwelling(String swelling) {
        Swelling = swelling;
    }

    public String getEyes_right() {
        return Eyes_right;
    }

    public void setEyes_right(String eyes_right) {
        Eyes_right = eyes_right;
    }

    public String getEyes_left() {
        return Eyes_left;
    }

    public void setEyes_left(String eyes_left) {
        Eyes_left = eyes_left;
    }

    public String getEars_right() {
        return Ears_right;
    }

    public void setEars_right(String ears_right) {
        Ears_right = ears_right;
    }

    public String getEars_left() {
        return Ears_left;
    }

    public void setEars_left(String ears_left) {
        Ears_left = ears_left;
    }

    public String getNose() {
        return Nose;
    }

    public void setNose(String nose) {
        Nose = nose;
    }

    public String getMouth_lips() {
        return Mouth_lips;
    }

    public void setMouth_lips(String mouth_lips) {
        Mouth_lips = mouth_lips;
    }

    public String getMouth_chin() {
        return Mouth_chin;
    }

    public void setMouth_chin(String mouth_chin) {
        Mouth_chin = mouth_chin;
    }

    public String getFacial_apeearance() {
        return facial_apeearance;
    }

    public void setFacial_apeearance(String facial_apeearance) {
        this.facial_apeearance = facial_apeearance;
    }

    public String getFeatures_relatedTo() {
        return Features_relatedTo;
    }

    public void setFeatures_relatedTo(String features_relatedTo) {
        Features_relatedTo = features_relatedTo;
    }

    public String getNeck() {
        return Neck;
    }

    public void setNeck(String neck) {
        Neck = neck;
    }

    public String getChest() {
        return Chest;
    }

    public void setChest(String chest) {
        Chest = chest;
    }

    public String getRight_Uper_limb() {
        return Right_Uper_limb;
    }

    public void setRight_Uper_limb(String right_Uper_limb) {
        Right_Uper_limb = right_Uper_limb;
    }

    public String getLeft_upper_limb() {
        return Left_upper_limb;
    }

    public void setLeft_upper_limb(String left_upper_limb) {
        Left_upper_limb = left_upper_limb;
    }

    public String getRight_hand() {
        return Right_hand;
    }

    public void setRight_hand(String right_hand) {
        Right_hand = right_hand;
    }

    public String getLeft_hand() {
        return Left_hand;
    }

    public void setLeft_hand(String left_hand) {
        Left_hand = left_hand;
    }

    public String getAbdomen() {
        return Abdomen;
    }

    public void setAbdomen(String abdomen) {
        Abdomen = abdomen;
    }

    public String getUmbilicus() {
        return Umbilicus;
    }

    public void setUmbilicus(String umbilicus) {
        Umbilicus = umbilicus;
    }

    public String getPenis() {
        return Penis;
    }

    public void setPenis(String penis) {
        Penis = penis;
    }

    public String getFemale_Genitalia() {
        return Female_Genitalia;
    }

    public void setFemale_Genitalia(String female_Genitalia) {
        Female_Genitalia = female_Genitalia;
    }

    public String getLegs_position() {
        return Legs_position;
    }

    public void setLegs_position(String legs_position) {
        Legs_position = legs_position;
    }

    public String getRight_lower_limb() {
        return Right_lower_limb;
    }

    public void setRight_lower_limb(String right_lower_limb) {
        Right_lower_limb = right_lower_limb;
    }

    public String getLeft_lower_limb() {
        return Left_lower_limb;
    }

    public void setLeft_lower_limb(String left_lower_limb) {
        Left_lower_limb = left_lower_limb;
    }

    public String getRight_foot() {
        return Right_foot;
    }

    public void setRight_foot(String right_foot) {
        Right_foot = right_foot;
    }

    public String getLeft_foot() {
        return Left_foot;
    }

    public void setLeft_foot(String left_foot) {
        Left_foot = left_foot;
    }

    public String getBack() {
        return Back;
    }

    public void setBack(String back) {
        Back = back;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }


}
*/

