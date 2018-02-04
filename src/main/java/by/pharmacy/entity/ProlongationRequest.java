package by.pharmacy.entity;

public class ProlongationRequest {

    private long id;
    private Prescription prescription;
    private int term;
    private ProlongationRequestStatus status;

    public ProlongationRequestStatus getStatus() {
        return status;
    }

    public void setStatus(ProlongationRequestStatus status) {
        this.status = status;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
