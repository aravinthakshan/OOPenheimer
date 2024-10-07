package packages.Billing;

public class Billing {
    private int billingId;
    private int patientId;
    private double totalAmount;
    private String insuranceProvider;
    private double insuranceCoverage;
    private String paymentStatus; // Pending, Paid

    // Constructor
    public Billing(int billingId, int patientId, double totalAmount, String insuranceProvider, double insuranceCoverage,
            String paymentStatus) {
        this.billingId = billingId;
        this.patientId = patientId;
        this.totalAmount = totalAmount;
        this.insuranceProvider = insuranceProvider;
        this.insuranceCoverage = insuranceCoverage;
        this.paymentStatus = paymentStatus;
    }

    // Getters and Setters
    public int getBillingId() {
        return billingId;
    }

    public void setBillingId(int billingId) {
        this.billingId = billingId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getInsuranceProvider() {
        return insuranceProvider;
    }

    public void setInsuranceProvider(String insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }

    public double getInsuranceCoverage() {
        return insuranceCoverage;
    }

    public void setInsuranceCoverage(double insuranceCoverage) {
        this.insuranceCoverage = insuranceCoverage;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
