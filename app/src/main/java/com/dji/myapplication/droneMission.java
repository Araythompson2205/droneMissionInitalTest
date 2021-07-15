package com.dji.myapplication;
import dji.sdk.mission.MissionControl;
//import dji.sdk.mission.timeline.*;
import dji.sdk.mission.timeline.actions.*;
//import dji.sdk.mission.timeline.actions.MissionAction;
public class droneMission
{
    private MissionControl missiona,missionEnd;
    private float height = (float) -15.00;
    //private float angle = (float)50.00;
    //setup for the missions, adding actions to be executed sequentially
    //triggers can also be added to intersperse actions using other code, will explore that aspect
    //more after verifiying the usage of the mission timeline
    public droneMission()
    {
        missiona=MissionControl.getInstance();
        missionEnd=MissionControl.getInstance();
        missiona.scheduleElement(new TakeOffAction());
        //note: I believe that if called succsecivly, gotoaction will make the drone
        //raise itself twice as high, or atleast 15 meters from current position.
        //double tap with caution.
       missiona.scheduleElement(new GoToAction(height));
        missiona.scheduleElement(new AircraftYawAction(-100,false));
        missiona.scheduleElement(new AircraftYawAction(100,false));
        missiona.scheduleElement(new AircraftYawAction(-100,false));
        missiona.scheduleElement(new AircraftYawAction(100,false));
        missionEnd.scheduleElement(new GoHomeAction());
    }
    //starts the mission, or stops it
    //expected behavior may not be accurate, testing needed if this works
    protected void runMission()
    {
        if(missiona.isTimelineRunning())
        {
            missiona.getInstance().stopTimeline();
            missionEnd.getInstance().startTimeline();
        }
        else
            missiona.getInstance().startTimeline();
    }
}
