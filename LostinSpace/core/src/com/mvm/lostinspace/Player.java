package com.mvm.lostinspace;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.mvm.lostinspace.components.MovementComponent;
import com.mvm.lostinspace.components.PlayerComponent;
import com.mvm.lostinspace.components.PlayerComponent.PlayerStatus;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.spriter.SpriterComponent;
import com.uwsoft.editor.renderer.scripts.IScript;

public class Player implements IScript {

	Entity player;
	private TransformComponent transformComponent;
	private PlayerComponent playerComponent;
	private SpriterComponent spriterComponent;
	private MovementComponent movementComponent;
	
	float movement = 80, rotationSpeed;
	
	
	
	private ComponentMapper<PlayerComponent> pm;
	private ComponentMapper<TransformComponent> tm;
	private ComponentMapper<SpriterComponent> sm;
	private ComponentMapper<MovementComponent> mm;
	
	
		
	@Override
	public void init(Entity entity) {		
		
		rotationSpeed = MathUtils.random(-30,30);
		
		pm = ComponentMapper.getFor(PlayerComponent.class);
		tm = ComponentMapper.getFor(TransformComponent.class);
		sm = ComponentMapper.getFor(SpriterComponent.class);
		mm = ComponentMapper.getFor(MovementComponent.class);
		
		this.player = entity;
		this.transformComponent = tm.get(entity);		
		this.playerComponent = pm.get(entity);
		this.spriterComponent = sm.get(entity);
		this.movementComponent = mm.get(entity);
										
		spriterComponent.player.setAnimation("Walking");
		// spriterComponent.player.speed = 10;
	}

	@Override
	public void act(float delta) {
			
		if (playerComponent.status == PlayerStatus.DEMO) {				
			transformComponent.x += movement*delta;
			if (transformComponent.x > 900) {
				transformComponent.x = 900;
				movement = - movement;
				spriterComponent.player.flipX();	
			}			
			if (transformComponent.x < 500) {
				transformComponent.x = 500;
				movement = - movement;
				spriterComponent.player.flipX();
			}
																				
		}
		if (playerComponent.status == PlayerStatus.FALLING) {
			if (spriterComponent.player.getAnimation().name.equals("Walking"))
					transformComponent.x += movement*delta;
			if (movement < 0) {
				movement = - movement;
				spriterComponent.player.flipX();
				}
			}			
			if (transformComponent.x >= 1200)
			{
				if (spriterComponent.player.getAnimation().name.equals("Walking"))
				{
					spriterComponent.player.setAnimation("Falling");
				}
				if (spriterComponent.player.getAnimation().name.equals("Falling")) {
					int time = spriterComponent.player.getTime();
					if ( time > 975) {
						spriterComponent.player.setAnimation("Drifting");
						playerComponent.status = PlayerStatus.DRIFTING;
						movementComponent.vel_x = MathUtils.random(10,30);
						movementComponent.vel_y = MathUtils.random(10,30);							
					}
				}																
			}
		if (playerComponent.status == PlayerStatus.DRIFTING) {			
			transformComponent.rotation += rotationSpeed * delta;
				
			
		}
			
		if (Gdx.input.justTouched() && playerComponent.status == PlayerStatus.DEMO)
		{
			playerComponent.status = PlayerStatus.FALLING;	
				
			
		}
				
		
	}	
			

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
