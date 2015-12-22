package com.mvm.lostinspace.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mvm.smoothcam.SmoothCamWorld;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.ViewPortComponent;

public class CameraSystem extends IteratingSystem {
	 
	private ComponentMapper<ViewPortComponent> vm;	
	private ComponentMapper<TransformComponent> tm;
	
	
	private SmoothCamWorld scw;
	
	@SuppressWarnings("unchecked")
	public CameraSystem(SmoothCamWorld scw) 
	{
		super(Family.all(ViewPortComponent.class).get());
		
		this.scw = scw;
		tm = ComponentMapper.getFor(TransformComponent.class);
		vm = ComponentMapper.getFor(ViewPortComponent.class);
		
		
	};
		

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// TODO Auto-generated method stub
						
		ViewPortComponent viewPortComponent = vm.get(entity);				
		Viewport viewport = viewPortComponent.viewPort;		
		Camera camera = viewPortComponent.viewPort.getCamera();
		
						
		camera.position.x = scw.getX();
		camera.position.y = scw.getY();		
		((OrthographicCamera) camera).zoom = scw.getZoom();	
		System.out.println(scw.getZoom());
																		
		
	}
}

