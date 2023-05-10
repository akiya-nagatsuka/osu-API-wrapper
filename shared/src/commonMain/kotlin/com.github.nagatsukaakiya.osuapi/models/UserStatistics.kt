package com.github.nagatsukaakiya.osuapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserStatistics(
    @SerialName("count_100")
    val count100: Int,
    @SerialName("count_300")
    val count300: Int,
    @SerialName("count_50")
    val count50: Int,
    @SerialName("count_miss")
    val countMiss: Int,
    @SerialName("grade_counts")
    val gradeCounts: GradeCounts,
    /** Hit accuracy percentage */
    @SerialName("hit_accuracy")
    val hitAccuracy: Float,
    /** Is actively ranked */
    @SerialName("is_ranked")
    val isRanked: Boolean,
    val level: Level,
    /** Highest maximum combo. */
    @SerialName("maximum_combo")
    val maximumCombo: Int,
    /** Number of maps played. */
    @SerialName("play_count")
    val playCount: Int,
    /** Cumulative time played. */
    @SerialName("play_time")
    val playTime: Int,
    /** Performance points */
    val pp: Float,
    /** Current rank according to pp. */
    @SerialName("global_rank")
    val globalRank: Int? = null,
    /** Current ranked score. */
    @SerialName("ranked_score")
    val rankedScore: Long,
    /** Number of replays watched by other users. */
    @SerialName("replays_watched_by_others")
    val replaysWatchedByOthers: Int,
    /** Total number of hits. */
    @SerialName("total_hits")
    val totalHits: Int,
    /** Total score. */
    @SerialName("total_score")
    val totalScore: Long,
    /** The associated user. */
    val user: UserCompact,
)
