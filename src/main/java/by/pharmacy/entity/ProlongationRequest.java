package by.pharmacy.entity;

import java.io.Serializable;

public class ProlongationRequest implements Serializable {
    private static final long serialVersionUID = 2900259127158904703L;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProlongationRequest that = (ProlongationRequest) o;

        if (id != that.id) return false;
        if (term != that.term) return false;
        if (prescription != null ? !prescription.equals(that.prescription) : that.prescription != null) return false;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (prescription != null ? prescription.hashCode() : 0);
        result = 31 * result + term;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProlongationRequest{" +
                "id=" + id +
                ", prescription=" + prescription +
                ", term=" + term +
                ", status=" + status +
                '}';
    }
}
