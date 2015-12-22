package com.mvm.lostinspace.components;

import com.badlogic.ashley.core.Component;
import com.mvm.smoothcam.SmoothCamPoint;

public class SmoothCamPointComponent implements Component {

	public SmoothCamPoint scp = new SmoothCamPoint();
	
	public SmoothCamPointComponent( float x, float y)
	{
		scp.setPosition(x, y);
		scp.setZoom(-0.5f);
		scp.setInnerRadius(250);
		scp.setOuterRadius(500);				
	}
}
