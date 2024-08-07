package me.oscarcusick.main.Engine;

import java.sql.Time;

// delta time shit
// DeltaTime is the difference in time between frames, measured in milliseconds compared to Unity's seconds.
// To make graphics or anything change at a static rate, independent of FPS, multiply the change by DeltaTime, it will move quicker but just change it by less
public class Timing {

    public enum TimeUnits {
        NanoS, // nanoseconds
        MicroS, // MicroSeconds
        MilliS, // milliseconds
        S, // seconds
    }

    long CurrentTime, LastTime, DeltaTime;
    public int TargetFPS;
    double RealFPS;
    double TargetNSPerFrame;

    /**
     * @param TargetFPS The Default Frames Per Second To Try And Achieve
     */
    public Timing(int TargetFPS) {
        this.TargetFPS = TargetFPS;

        CurrentTime = System.nanoTime();
    }

    /**
     * @param TimeValue The Amount Of Time To Be Converted
     * @param OriginalUnit The Starting Unit
     * @param PreferredUnit The Ending Unit
     * @return The Time In The New Units
     */
    public double ConvertTimeUnits(double TimeValue, TimeUnits OriginalUnit, TimeUnits PreferredUnit) {
        double Factor = Math.pow(1000, (OriginalUnit.ordinal() - PreferredUnit.ordinal()));

        if (Factor > 0) {
            return TimeValue * Factor;
        } else if (Factor < 0) {
            return Math.abs(TimeValue / Factor);
        }
        return 1;
    }

    /**
     * @deprecated Only Converts From Nanoseconds
     * @see ConvertTimeUnits
     * @param NanoSeconds Nanoseconds Value To Be Converted From
     * @param PreferredUnit Time Unit To Be Converted To
     * @return Returns The New Time Unit
     */
    double ConvertNanoSeconds(double NanoSeconds, TimeUnits PreferredUnit) {
        return switch (PreferredUnit.ordinal()) {
            case 0 -> NanoSeconds; // nanoseconds
            case 1-> NanoSeconds / 1000; // microseconds
            case 2 -> NanoSeconds / 1000000; // milliseconds
            case 3 -> NanoSeconds / 1000000000; // seconds
            default -> -1;
        };
    }

    // called to prevent the program running above the target frame-rate, called right after Update
    void Pause() {
        if (TargetNSPerFrame > DeltaTime) { // sleep if we are running too fast
            try {
                Thread.sleep((long) (ConvertNanoSeconds(TargetNSPerFrame, TimeUnits.MilliS) - ConvertNanoSeconds(DeltaTime, TimeUnits.MilliS)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Should Be Ran At The Beginning Of Every Frame Render Cycle. Updates Internal Mathematical Values
     * @param DoPause Weather It Should Halt After Calculations To Not Exceed Target FPS
     */
    // called at start of every frame draw
    public void Update(boolean DoPause) {
        TargetNSPerFrame = (1000f / TargetFPS) * 1000000;

        LastTime = CurrentTime;
        CurrentTime = System.nanoTime();
        DeltaTime = (CurrentTime - LastTime); // nano seconds
        RealFPS = 1 / ConvertNanoSeconds(DeltaTime, TimeUnits.S);

        if (DoPause) {
            Pause();
        }
    }
    // overload that will update the time class but not pause
    public void Update() {
        Update(false);
    }

    // Get DeltaTime in most needed time units
    public double GetDeltaTime(TimeUnits PreferredUnit) {
        return switch (PreferredUnit.ordinal()) {
            case 0 -> (double) ConvertNanoSeconds(DeltaTime, TimeUnits.NanoS);
            case 1 -> (double) ConvertNanoSeconds(DeltaTime, TimeUnits.MicroS);
            case 2 -> (double) ConvertNanoSeconds(DeltaTime, TimeUnits.MilliS);
            case 3 -> (double) ConvertNanoSeconds(DeltaTime, TimeUnits.S);
            default -> -1;
        };
    }

    /**
     * @return The Calculated Frames Per Second. (Calculated Every Time Update Is Called)
     */
    public double GetRealFPS() {
        return RealFPS;
    }
}