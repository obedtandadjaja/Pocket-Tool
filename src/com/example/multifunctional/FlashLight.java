package com.example.multifunctional;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ToggleButton;
import com.app.obedtandadjaja.multifunctional.R;

public class FlashLight extends Fragment {

	ToggleButton button;
	ImageView image;
	Camera cam;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(false);

	}

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.flash_light, container, false);
		button = (ToggleButton) rootView.findViewById(R.id.toggleButton1);
		image = (ImageView) rootView.findViewById(R.id.imageView1);
		
		button.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(button.isChecked() && getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH))
				{
					button.setBackgroundResource(R.drawable.flashlight_fill);
					cam = Camera.open();     
					Parameters p = cam.getParameters();
					p.setFlashMode(Parameters.FLASH_MODE_TORCH);
					cam.setParameters(p);
					cam.startPreview();
				}
				else
				{
					button.setBackgroundResource(R.drawable.flashlight);
					cam.stopPreview();
				    cam.release();
				}
			}
		});
		
		return rootView;
	}

}
