public interface SmartClassroomDevice {
    // Fat interface (ISP violation)
    // Split into: Powerable, BrightnessControllable, TemperatureControllable, Scannable, InputConnectable
}

interface Powerable {
    void powerOn();
    void powerOff();
}

interface BrightnessControllable {
    void setBrightness(int pct);
}

interface TemperatureControllable {
    void setTemperatureC(int c);
}

interface Scannable {
    int scanAttendance();
}

interface InputConnectable {
    void connectInput(String port);
}
