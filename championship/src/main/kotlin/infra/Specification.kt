package infra

/**
* @author claudio on 20/02/17.
* @email <claudioed.oliveira@gmail.com>
* surfacts
*
*/
interface Specification<T> {

    infix fun isSatisfied(instance:T):Boolean

}