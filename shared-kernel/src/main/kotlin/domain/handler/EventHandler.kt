package domain.handler

import domain.event.DomainEvent

/**
 * @author claudio on 23/02/17.
 * @email <claudioed.oliveira@gmail.com>
 * surfacts
 *
 */
interface EventHandler {

    /**
     * Handle the domain.event
     */
    fun handle(event: DomainEvent)

}