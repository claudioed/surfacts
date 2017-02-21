package domain.service

import domain.Prediction
import domain.RegisteredPrediction

/**
 * @author claudio on 20/02/17.
 * @email <claudioed.oliveira@gmail.com>
 * surfacts
 *
 */
interface PredictionService {

    fun register(prediction: Prediction): RegisteredPrediction

    fun myPredictions(groupId: String, userId: String): List<RegisteredPrediction>

}