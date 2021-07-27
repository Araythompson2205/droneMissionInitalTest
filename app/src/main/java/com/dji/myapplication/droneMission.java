package com.dji.myapplication;
import androidx.annotation.Nullable;

import dji.common.error.DJIError;
import dji.sdk.mission.MissionControl;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//import dji.sdk.mission.timeline.*;
import dji.sdk.mission.timeline.TimelineElement;
import dji.sdk.mission.timeline.TimelineEvent;
import dji.sdk.mission.timeline.actions.*;
//import dji.sdk.mission.timeline.actions.MissionAction;
public class droneMission
{
    private MissionControl missiona,missionEnd;
    private float height = (float) -1.00;
    private List<TimelineElement> elements;
    //private float angle = (float)50.00;
    //setup for the missions, adding actions to be executed sequentially
    //triggers can also be added to intersperse actions using other code, will explore that aspect
    //more after verifiying the usage of the mission timeline
    public droneMission()
    {
        elements= new ArrayList<>();
        missiona=MissionControl.getInstance();
        MissionControl.Listener listener = (timelineElement, timelineEvent, djiError) -> {

        };
        elements.add(new TakeOffAction());
        //note: I believe that if called succsecivly, gotoaction will make the drone
        //raise itself twice as high, or atleast 15 meters from current position.
        //double tap with caution.
        elements.add(new GoToAction(height));
        elements.add(new AircraftYawAction(-100,false));
        elements.add(new AircraftYawAction(100,false));
        elements.add(new AircraftYawAction(-100,false));
        elements.add(new AircraftYawAction(100,false));
       // missionEnd.scheduleElement(new GoHomeAction());
        missiona.scheduleElements(elements);
        missiona.addListener(listener);
    }
    //starts the mission, or stops it
    //expected behavior may not be accurate, testing needed if this works
    protected void runMission()
    {
       // if(missiona.isTimelineRunning())
       // {
          //  MissionControl.getInstance().stopTimeline();
           // missionEnd.getInstance().startTimeline();
       // }
       // else
           MissionControl.getInstance().startTimeline();
    }
}
