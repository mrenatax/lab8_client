package SpaceMarineData;

import CommandProcessing.Commands;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.io.*;
import java.time.Instant;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Objects;

public class SpaceMarine implements Comparable<SpaceMarine>,Serializable {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long health; //Поле не может быть null, Значение поля должно быть больше 0
    private Long height; //Поле не может быть null
    private Weapon weaponType; //Поле может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter; //Поле может быть null
    private String login;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceMarine that = (SpaceMarine) o;
        return getId() == that.getId() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getCoordinates(), that.getCoordinates()) &&
                Objects.equals(getCreationDate(), that.getCreationDate()) &&
                Objects.equals(getHealth(), that.getHealth()) &&
                Objects.equals(getHeight(), that.getHeight()) &&
                weaponType == that.weaponType &&
                getMeleeWeapon() == that.getMeleeWeapon() &&
                Objects.equals(getChapter(), that.getChapter()) &&
                Objects.equals(getLogin(), that.getLogin()) &&
                Objects.equals(getColor(), that.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCoordinates(), getCreationDate(), getHealth(), getHeight(), weaponType, getMeleeWeapon(), getChapter(), getLogin(), getColor());
    }

    private Color color;

    private static final long serialVersionUID = -6634144303926521364L;
    public SpaceMarine(){} //default no-arg constructor
    public SpaceMarine(String name, int id, Long health, Long height, Chapter chapter, Coordinates coordinates, MeleeWeapon meleeWeapon, Weapon weaponType, String login)  {
        if ((name == null) ||(name.trim().length()==0)) throw new IllegalArgumentException("Поле NAME должно быть не null, строка не может быть пустой ");
        if ((health <= 0)||(health == null)) throw new IllegalArgumentException("Поле HEALTH должно быть больше нуля, не может быть null");
        if (height == null) throw new IllegalArgumentException("Поле HEIGHT не может быть null");
        if (coordinates == null) throw new IllegalArgumentException("Поле COORDINATES не может быть null");
        if(meleeWeapon == null) throw  new IllegalArgumentException("Поле MELEEWEAPON не может быть null");
        this.creationDate = java.util.Date.from(Instant.now());
        this.id = id;
        this.name = name;
        this.health = health;
        this.height = height;
        this.coordinates = coordinates;
        this.weaponType = weaponType;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
        this.login = login;
        if(id <= 0) throw new IllegalArgumentException("Поле ID должно быть больше нуля");
    }
    public void setColor(Color col){ color = col; }
    public Color getColor(){return color;}
    public void setLogin(String log){
        login = log;
    }
    public String getLogin(){
        return login;
    }
    public void setNewId(){
        id = (int)(Math.random()*(1000+1));
    }
    public void setId(){
        id = (int)(Math.random()*(1000+1));
    }
    public void setIdClient(int x){
        id = x;
    }

    public void setCreationDate(){
        creationDate = java.util.Date.from(Instant.now());
    }
    public void setWeaponType(String s){
        switch (s){
            case ("BOLTGUN"):
                weaponType = Weapon.BOLTGUN;
                break;
            case ("HEAVY_BOLTGUN"):
                weaponType = Weapon.HEAVY_BOLTGUN;
                break;
            case ("HEAVY_FLAMER"):
                weaponType = Weapon.HEAVY_FLAMER;
                break;
            case ("GRENADE_LAUNCHER"):
                weaponType = Weapon.GRENADE_LAUNCHER;
                break;
            case ("MULTI_MELTA"):
                weaponType = Weapon.MULTI_MELTA;
                break;
        }
    }
    public void setMeleeWeapon(String s){
        switch (s){
            case("CHAIN_AXE"):
                meleeWeapon = MeleeWeapon.CHAIN_AXE;
                break;
            case("CHAIN_SWORD"):
                meleeWeapon = MeleeWeapon.CHAIN_SWORD;
                break;
            case("LIGHTING_CLAW"):
                meleeWeapon = MeleeWeapon.LIGHTING_CLAW;
                break;
            case ("POWER_FIST"):
                meleeWeapon = MeleeWeapon.POWER_FIST;
                break;
        }
    }
    public void setHealth(Long hlth){
        health = hlth;
    }
    public void setHeight(Long hgth){
        height = hgth;
    }
     public void setNameF() throws IOException {
        name = Commands.slmmsk.nextLine();
    }
    public void setHealthF(){
        try{
            health = Long.parseLong(Commands.slmmsk.nextLine());}
        catch (InputMismatchException  e){
            System.out.println("поле health в скрипте заполнено некоррректно");
        }
    }
    public void setHeightF(){
        try{
            height = Long.parseLong(Commands.slmmsk.nextLine());}
        catch (InputMismatchException e){
            System.out.println("поле height в скрипте заполнено некорректно");
        }
    }
     public Weapon getWeapon(){
        return weaponType;
    }
    public void setMeleeWeaponF() throws IOException {
        String meleeweapon =  Commands.slmmsk.nextLine();
        switch (meleeweapon){
            case("CHAIN_AXE"):
                meleeWeapon = MeleeWeapon.CHAIN_AXE;
                break;
            case("chain_axe"):
                meleeWeapon = MeleeWeapon.CHAIN_AXE;
                break;
            case("CHAIN_SWORD"):
                meleeWeapon = MeleeWeapon.CHAIN_SWORD;
                break;
            case("chain_sword"):
                meleeWeapon = MeleeWeapon.CHAIN_SWORD;
                break;
            case("LIGHTING_CLAW"):
                meleeWeapon = MeleeWeapon.LIGHTING_CLAW;
                break;
            case("lighting_claw"):
                meleeWeapon = MeleeWeapon.LIGHTING_CLAW;
                break;
            case ("POWER_FIST"):
                meleeWeapon = MeleeWeapon.POWER_FIST;
                break;
            case ("power_fist"):
                meleeWeapon = MeleeWeapon.POWER_FIST;
                break;
        }
    }
      public void setWeaponTypeF(){
        String s = Commands.slmmsk.nextLine();
        switch (s){
            case ("BOLTGUN"):
                weaponType = Weapon.BOLTGUN;
                break;
            case ("boltgun"):
                weaponType = Weapon.BOLTGUN;
                break;
            case ("HEAVY_BOLTGUN"):
                weaponType = Weapon.HEAVY_BOLTGUN;
                break;
            case ("heavy_boltgun"):
                weaponType = Weapon.HEAVY_BOLTGUN;
                break;
            case ("HEAVY_FLAMER"):
                weaponType = Weapon.HEAVY_FLAMER;
                break;
            case ("heavy_flamer"):
                weaponType = Weapon.HEAVY_FLAMER;
                break;
            case ("GRENADE_LAUNCHER"):
                weaponType = Weapon.GRENADE_LAUNCHER;
                break;
            case  ("grenade_launcher"):
                weaponType = Weapon.GRENADE_LAUNCHER;
                break;
            case ("MULTI_MELTA"):
                weaponType = Weapon.MULTI_MELTA;
                break;
            case ("multi_melta"):
                weaponType = Weapon.MULTI_MELTA;
                break;
        }
        if (s.trim().length() == 0) weaponType = null;
    }
    public Coordinates setCoordinatesF(){
        this.coordinates = new Coordinates();
        coordinates.setXYForScript();
        return coordinates;
    }
    public void setChapterF(){
        this.chapter = new Chapter();
        chapter.setChapterFieldsForScript();
    }
    public void setName(String str){
       name = str;
    }
    public void setCoordinates(Float xn, Float yn){
        this.coordinates = new Coordinates();
        coordinates.setXY(xn, yn);
    }
    public void setChapter(String nm, Long mrncnt){
        this.chapter = new Chapter();
        chapter.setChapterFields(nm,mrncnt);
    }
    public String getName(){
        return name ;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public Float getX(){
        return coordinates.getX();
    }
    public Float getY(){
        return coordinates.getY();
    }
    public String getChapterName(){
        return chapter.getChapterName();
    }
    public Long getMarinesCount(){
        return chapter.getMarinesCount();
    }
    public Long getHeight() {
        return height;
    }
    public Long getHealth() {
        return health ;
    }
    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }
    public Chapter getChapter() {
        return chapter;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public Integer getId(){
        return id;
    }

    @Override
    public String toString() {
        return ( "\n" + "Название: " + name + "\n" +
                "id: " + id + "\n" +
                "Координаты: " + coordinates + "\n" +
                "chapter: " + chapter + "\n" +
                "health: " + health + "\n" +
                "height: " + height + "\n" +
                "weaponType: " + weaponType + "\n" +
                "meleeWeapon: " + meleeWeapon + "\n" +
                "creationDate: " + creationDate + "\n" +
                "creator's login: " + login
        );
    }

    @Override
    public int compareTo(SpaceMarine o) {
        return Integer.compare(this.name.length(), o.name.length());
    }

}

