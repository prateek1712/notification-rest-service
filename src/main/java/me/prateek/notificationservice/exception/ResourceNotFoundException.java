package me.prateek.notificationservice.exception;


public class ResourceNotFoundException extends RuntimeException {

    private Integer resourceId;

    public ResourceNotFoundException(Integer resourceId, String resource) {
        super(resource + " with ID " + resourceId+ " doesn't exist");
        this.resourceId = resourceId;
    }
}
