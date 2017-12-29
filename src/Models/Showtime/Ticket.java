package Models.Showtime;

import Utilities.Entity;

/**
 * Class that represents a ticket
 * @author Kenneth
 */
public class Ticket extends Entity {
    
    private static final long serialVersionUID = 12324134119L;
    
    private SeatBooking seatBooked;
    private TicketType ticketType;
    private Showtime showTimeBooked;
    
    public Ticket(SeatBooking seatBooked, TicketType ticketType,
        Showtime showTimeBooked) {
        this.seatBooked = seatBooked;
        this.ticketType = ticketType;
        this.showTimeBooked = showTimeBooked;
    }
    
    
}
