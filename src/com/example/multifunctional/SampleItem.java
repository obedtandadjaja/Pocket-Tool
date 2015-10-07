package com.example.multifunctional;

public class SampleItem 
{
	public String title;
    public String icon;

    public SampleItem(String title, String icon) 
    {
        this.title = title;
        this.icon = icon;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
