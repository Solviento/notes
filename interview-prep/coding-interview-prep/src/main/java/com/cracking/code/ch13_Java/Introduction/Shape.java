package com.cracking.code.ch13_Java.Introduction;

public abstract class Shape {
	public void printMe() {
		System.out.println("I am a shape.");
	}
	
	public abstract double computeArea();
}
