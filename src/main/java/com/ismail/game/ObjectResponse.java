package com.ismail.game;

/**
 * Object Response used to communicate between two threads; 
 * one waiting for a response; and the other thread providing the response
 * 
 * @author Ismail 
 */
public class ObjectResponse<T>
{
    private T response = null;

    private Object lock = new Object();

    public ObjectResponse()
    {
    }

    /**
     * Sets the response object
     * 
     * @param pObject
     */
    public void set(T response)
    {
        synchronized (lock)
        {
            this.response = response;

            lock.notify();
        }
    }

    /**
     * Gets the response (waits forever, NOT recommended)
     * 
     * Best to use get(timeout)
     * 
     * @return response object 
     * @throws InterruptedException any interrupted exception, so the receiving thread will be notified
     */
    public T get() throws InterruptedException
    {
        synchronized (lock)
        {
            if (response == null)
                lock.wait();
            
            return response;
        }
    }

    /**
     * Return the response after a timeout
     * 
     * @return response object
     * @throws InterruptedException any interrupted exception, so the receiving thread will be notified
     */
    public T get(long pTimeout) throws InterruptedException
    {
        synchronized (lock)
        {
            if (response == null)
                lock.wait(pTimeout);

            return response;
        }
    }

    /**
     * Resets the response to null
     * In case we want to re-use this Response Object
     */
    public void reset()
    {
        response = null;
    }
}