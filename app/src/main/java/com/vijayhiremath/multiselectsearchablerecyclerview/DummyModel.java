package com.vijayhiremath.multiselectsearchablerecyclerview;

/**
 * Created by vijay.hiremath on 06/10/16.
 */
public class DummyModel
{
    Integer id;
    String  name;
    boolean checked;

    public DummyModel(Integer id, String name, boolean checked)
    {
        this.id = id;
        this.name = name;
        this.checked = checked;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isChecked()
    {
        return checked;
    }

    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }
}
