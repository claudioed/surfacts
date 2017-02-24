package domain.handler

import domain.event.DomainEvent

/**
 * @author claudio on 23/02/17.
 * @email <claudioed.oliveira@gmail.com>
 * surfacts
 *
 */
interface EventPublisher : EventHandler {

    override fun handle(event: DomainEvent): Unit {
        publish(event)
        handle(event)
    }

    fun publish(event: DomainEvent)
}