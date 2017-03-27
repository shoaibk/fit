package ca.shoaib.fit;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class StepService extends Service implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private float steps;

    public static final String ACTION_START_COUNT = "ca.shoaib.fit.action.start_count";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //        if(mSensorManager == null){
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_FASTEST);
        Log.i("StepService", "Starting sensor for step counting");
//        }
        // do your jobs here
        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        steps = sensorEvent.values[0];
        Log.i("StepService", "" + (int)steps);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
