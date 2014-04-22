package com.zalaty.english4children.classes;

import android.content.Intent;

public enum Games{
	colores, numbers, mango, orange;
	private static final String name = Games.class.getName();
	public void attachTo(Intent intent) {
	    intent.putExtra(name, ordinal());
	  }
	  public static Games detachFrom(Intent intent) {
	    if(!intent.hasExtra(name)) throw new IllegalStateException();
	    return values()[intent.getIntExtra(name, -1)];
	  }
}
