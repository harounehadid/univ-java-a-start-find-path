import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Utils.GetBaseDirPath;

public class Battery {
    private int chargingLevel;
    private String batteryStatus;
    private JLabel batteryLabel;
    private boolean isCharging;

    public Battery() {
        this.chargingLevel = 100;
        this.isCharging = false;
        this.handleGUI();
    }

    private void handleGUI() {
        this.batteryLabel = new JLabel();
        this.batteryLabel.setFont(CustomFrame.geFont());
        this.updateBatteryStatus();
    }

    public void use(int energyNeeded) {
        if (energyNeeded < 0) {
            System.out.println("(!) Energy level can't be a negative number (!)");
            return;
        }

        if (energyNeeded >= this.chargingLevel) {
            this.chargingLevel = 0;
        }
        else {
            this.chargingLevel -= energyNeeded;
        }

        this.updateBatteryStatus();

        if (this.chargingLevel <= 0) {
            this.charge();
        }
    }

    public void updateBatteryStatus() {
        if (this.chargingLevel > 75 && this.batteryStatus != "very high") {
            if (!this.isCharging) this.batteryStatus = "very high";
            this.treatLabel("image", GetBaseDirPath.root() + "/src/media/battery-fully-charged.png");
        }
        else if (this.chargingLevel > 50 && this.chargingLevel <= 75 && this.batteryStatus != "high") {
            if (!this.isCharging) this.batteryStatus = "high";
            this.treatLabel("image", GetBaseDirPath.root() + "/src/media/battery-75-charged.png");
        }
        else if (this.chargingLevel > 25 && this.chargingLevel <= 50 && this.batteryStatus != "low") {
            if (!this.isCharging) this.batteryStatus = "low";
            this.treatLabel("image", GetBaseDirPath.root() + "/src/media/battery-50-charged.png");
        }
        else if (this.chargingLevel > 0 && this.chargingLevel <= 25 && this.batteryStatus != "very low") {
            if (!this.isCharging) this.batteryStatus = "very low";
            this.treatLabel("image", GetBaseDirPath.root() + "/src/media/battery-25-charged.png");
        }
        else if (this.chargingLevel <= 0 && this.batteryStatus != "empty") {
            if (!this.isCharging) this.batteryStatus = "empty";
            this.treatLabel("image", GetBaseDirPath.root() + "/src/media/battery-empty.png");
        }

        if (!this.isCharging) this.treatLabel("text", this.chargingLevel + "%");
    }

    private void treatLabel(String itemType, String data) {
        if (itemType == "image") {
            this.batteryLabel.setIcon(null);
            ImageIcon image = new ImageIcon(data);
            this.batteryLabel.setSize(image.getIconWidth(), image.getIconHeight());
            this.batteryLabel.setIcon(image);
        }
        else if (itemType == "text") {
            this.batteryLabel.setText(data);
        }
    }

    private void charge() {
        this.isCharging = true;

        this.treatLabel("text", "Charging...");

        int maxChargingLevel = 100;

        while (this.chargingLevel < maxChargingLevel) {
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            this.chargingLevel += 20;

            if (this.chargingLevel > maxChargingLevel) {
                this.chargingLevel = maxChargingLevel;
            }

            this.updateBatteryStatus();
        }

        this.isCharging = false;
        this.updateBatteryStatus();

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Getters
    public JLabel getGUI() {
        return this.batteryLabel;
    }
}
