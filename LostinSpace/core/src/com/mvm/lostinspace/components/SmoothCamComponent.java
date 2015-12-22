package com.mvm.lostinspace.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Intersector.MinimumTranslationVector;
import com.mvm.smoothcam.SmoothCamSubject;

public class SmoothCamComponent implements Component{

	public SmoothCamSubject smoothCamSubject;
		
	{
		this.smoothCamSubject = new SmoothCamSubject();	
		this.smoothCamSubject.setVelocityRadius(100f);
		this.smoothCamSubject.setAimingRadius(200f);	
		
	}
	
}
