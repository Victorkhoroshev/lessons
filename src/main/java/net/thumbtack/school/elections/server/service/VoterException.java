package net.thumbtack.school.elections.server.service;

public class VoterException extends Exception {

    private final VoterExceptionErrorCode voterExceptionErrorCode;

    public VoterException(VoterExceptionErrorCode voterExceptionErrorCode) {
        this.voterExceptionErrorCode = voterExceptionErrorCode;
    }
    @Override
    public String getLocalizedMessage() {
        return voterExceptionErrorCode.getMessage();
    }

    public VoterExceptionErrorCode getErrorCode() {
        return voterExceptionErrorCode;
    }

}
