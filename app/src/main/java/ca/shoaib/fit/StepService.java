package ca.shoaib.fit;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class StepService extends IntentService implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private float steps;

    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_START_COUNT = "ca.shoaib.fit.action.start_count";
//    private static final String ACTION_BAZ = "ca.shoaib.fit.action.BAZ";

    // TODO: Rename parameters
//    private static final String EXTRA_PARAM1 = "ca.shoaib.fit.extra.PARAM1";
//    private static final String EXTRA_PARAM2 = "ca.shoaib.fit.extra.PARAM2";

    public StepService() {
        super("StepService");

    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionStepCount(Context context) {
        Intent intent = new Intent(context, StepService.class);
        intent.setAction(ACTION_START_COUNT);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_START_COUNT.equals(action)) {
                handleActionStartCount();
            }
        }
    }

    /**
     * Handle action start count in the provided background thread with the provided
     * parameters.
     */
    private void handleActionStartCount() {
//        if(mSensorManager == null){
            mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_FASTEST);
            Log.i("StepService", "Starting sensor for step counting");
//        }

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
