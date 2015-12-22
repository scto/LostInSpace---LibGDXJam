package com.mvm.lostinspace.components;

import com.badlogic.ashley.core.Component;


public class PlayerComponent implements Component {

	public enum PlayerStatus { FALLING, PAUZED, DEMO, PLAYING, DRIFTING };
	public PlayerStatus status = PlayerStatus.DEMO;
	
}
