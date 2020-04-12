package mod.mythusteam.mythus.utils;

import net.minecraft.entity.EntityType;

public enum MagicMobAttributes
{
    ZOMBIE(50, 10, 20, 5, 90, 60, 20, EntityType.ZOMBIE),
    WITCH(20, 60, 30, 20, 60, 60, 60, EntityType.WITCH),
    CREEPER(40, 90, 10, 0, 0, 0, 0, EntityType.CREEPER);

    int power, spellpower, defense, control, restoration, reflective_app, external_app;
    EntityType associated;

    /**
     * These parameters help determine the resulting "force" of a spell when cast
     * Rather than thinking of these numbers as stats, think of it as how effective
     * a person with this attribute can cast a spell (i.e. high power = can effectively use attacks
     * that deal high raw damage)
     * Values out of 100
     * @param power Raw damage or strength, think of it as brute force
     * @param spellpower Spell damage
     * @param defense Resistance to damage or effects
     * @param control Precision and accuracy or "multi-tasking" spells
     * @param restoration Healing
     * @param reflective_app Self-application of spells, such as buffs
     * @param external_app Application of spells to others, such as de-buffs or ally buffs
     */
    MagicMobAttributes(int power, int spellpower, int defense, int control, int restoration, int reflective_app, int external_app, EntityType associated)
    {
        this.power = power;
        this.spellpower = spellpower;
        this.defense = defense;
        this.control = control;
        this.restoration = restoration;
        this.reflective_app = reflective_app;
        this.external_app = external_app;
        this.associated = associated;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getControl() {
        return control;
    }

    public void setControl(int control) {
        this.control = control;
    }

    public int getRestoration() {
        return restoration;
    }

    public void setRestoration(int restoration) {
        this.restoration = restoration;
    }

    public int getReflectiveApp() {
        return reflective_app;
    }

    public void setReflectiveApp(int reflective_app) {
        this.reflective_app = reflective_app;
    }

    public int getExternalApp() {
        return external_app;
    }

    public void setExternalApp(int external_app) {
        this.external_app = external_app;
    }

    public EntityType getAssociated()
    {
        return this.associated;
    }

    public static MagicMobAttributes getAttributeFromType(EntityType type)
    {
        for(MagicMobAttributes element : MagicMobAttributes.values())
        {
            if(element.getAssociated() == type) return element;

        }
        return null;
    }
}
