package org.bigbluebutton.apps.protocol

trait InMessage

case class GetChatHistoryRequest(meetingID: String, requesterID: String) extends InMessage
case class SendPublicMessageRequest(meetingID: String, requesterID: String, message: Map[String, String]) extends InMessage
case class SendPrivateMessageRequest(meetingID: String, requesterID: String, message: Map[String, String]) extends InMessage

case class GetCurrentLayoutRequest(meetingID: String, requesterID: String) extends InMessage
case class SetLayoutRequest(meetingID: String, requesterID: String, layoutID: String) extends InMessage
case class LockLayoutRequest(meetingID: String, requesterID: String, layoutID: String) extends InMessage
case class UnlockLayoutRequest(meetingID: String, requesterID: String) extends InMessage

// Poll Messages
case class PreCreatedPoll(meetingID: String, poll: PollVO) extends InMessage
case class CreatePoll(meetingID: String, requesterID: String, poll: PollVO) extends InMessage
case class UpdatePoll(meetingID: String, requesterID: String, poll: PollVO) extends InMessage
case class GetPolls(meetingID: String, requesterID: String) extends InMessage
case class DestroyPoll(meetingID: String, requesterID: String, pollID: String) extends InMessage
case class RemovePoll(meetingID: String, requesterID: String, pollID: String) extends InMessage
case class SharePoll(meetingID: String, requesterID: String, pollID: String) extends InMessage
case class ShowPollResult(meetingID: String, requesterID: String, pollID: String) extends InMessage
case class HidePollResult(meetingID: String, requesterID: String, pollID: String) extends InMessage
case class StopPoll(meetingID:String, requesterID: String, pollID: String) extends InMessage
case class StartPoll(meetingID:String, requesterID: String, pollID: String) extends InMessage
case class ClearPoll(meetingID: String, requesterID: String, pollID: String, force: Boolean=false) extends InMessage
case class GetPollResult(meetingID:String, requesterID: String, pollID: String) extends InMessage
case class RespondToPoll(meetingID: String, requesterID: String, response: PollResponseVO) extends InMessage

case class ResponseVO(id: String, text: String, responders: Array[Responder] = Array[Responder]())
case class QuestionVO(id: String, multiResponse: Boolean, question: String, responses: Array[ResponseVO])
case class PollVO(id: String, title: String, questions: Array[QuestionVO], started: Boolean = false, stopped: Boolean = false)
case class QuestionResponsesVO(val questionID:String, val responseIDs:Array[String])
case class PollResponseVO(val pollID: String, val responses: Array[QuestionResponsesVO])
case class ResponderVO(responseID: String, user: Responder)
case class Responder(val userID: String, name: String)

// Users
case class UserJoining(meetingID: String, userID: String, name: String, role: String, extUserID: String) extends InMessage
case class UserLeaving(meetingID: String, userID: String) extends InMessage
case class GetUsers(meetingID: String, requesterID: String) extends InMessage
case class ChangeUserStatus(meetingID: String, userID: String, status: String, value: Object) extends InMessage
case class AssignPresenter(meetingID: String, newPresenterID: String, newPresenterName: String, assignedBy: String) extends InMessage

// Presentation
case class ClearPresentation(meetingID: String) extends InMessage
case class PresentationConversionUpdate(meetingID: String, msg: Map[String, Object]) extends InMessage
case class RemovePresentation(meetingID: String, presentationID: String) extends InMessage
case class GetPresentationInfo(meetingID: String, requesterID: String) extends InMessage
case class SendCursorUpdate(meetingID: String, xPercent: Double, yPercent: Double) extends InMessage
case class ResizeAndMoveSlide(meetingID: String, xOffset: Double, yOffset: Double, widthRatio: Double, heightRatio: Double) extends InMessage
case class GotoSlide(meetingID: String, slide: Int) extends InMessage
case class SharePresentation(meetingID: String, presentationID: String, share: Boolean) extends InMessage
case class GetSlideInfo(meetingID: String, requesterID: String) extends InMessage
case class PreuploadedPresentations(meetingID: String, presentations: Array[Object]) extends InMessage

case class SendVoiceUsersRequest(meetingID: String, requesterID: String) extends InMessage
case class MuteMeetingRequest(meetingID: String, requesterID: String, mute: Boolean) extends InMessage
case class IsMeetingMutedRequest(meetingID: String, requesterID: String) extends InMessage
case class MuteUserRequest(meetingID: String, requesterID: String, userID: String, mute: Boolean) extends InMessage
case class LockUserRequest(meetingID: String, requesterID: String, userID: String, lock: Boolean) extends InMessage
case class EjectUserRequest(meetingID: String, requesterID: String, userID: String) extends InMessage
case class VoiceUserJoinedMessage(meetingID: String, user: String, voiceConfId: String, callerIdNum: String, callerIdName: String, 
    muted: Boolean, talking: Boolean) extends InMessage
    
case class VoiceUserLeftMessage(meetingID: String, user: String, voiceConfId: String) extends InMessage
case class VoiceUserMutedMessage(meetingID: String, user: String, voiceConfId: String, muted: Boolean) extends InMessage
case class VoiceUserTalkingMessage(meetingID: String, user: String, voiceConfId: String, talking: Boolean) extends InMessage
case class VoiceStartedRecordingMessage(meetingID: String, voiceConfId: String, filename: String, timestamp: String, record: Boolean) extends InMessage

// Need these extra classes since InMessage needs meetingID as parameter and
// our messages from FreeSWITCH doesn't have it.
trait VoiceMessage
case class VoiceUserJoined(
    user: String, 
    voiceConfId: String, 
    callerIdNum: String, 
    callerIdName: String, 
    muted: Boolean, 
    speaking: Boolean) extends VoiceMessage
    
case class VoiceUserLeft(user: String, voiceConfId: String) extends VoiceMessage
case class VoiceUserMuted(user: String, voiceConfId: String, muted: Boolean) extends VoiceMessage
case class VoiceUserTalking(user: String, voiceConfId: String, talking: Boolean) extends VoiceMessage
case class VoiceStartedRecording(voiceConfId: String, filename: String, timestamp: String, record: Boolean) extends VoiceMessage

case class AnnotationVO(id: String, status: String, shapeType: String, shape: scala.collection.immutable.Map[String, Object])
case class SendWhiteboardAnnotationRequest(meetingID: String, requesterID: String, annotation: AnnotationVO) extends InMessage
case class SetWhiteboardActivePageRequest(meetingID: String, requesterID: String, page: Int) extends InMessage
case class SendWhiteboardAnnotationHistoryRequest(meetingID: String, requesterID: String, presentationID: String, page: Int) extends InMessage
case class ClearWhiteboardRequest(meetingID: String, requesterID: String) extends InMessage
case class UndoWhiteboardRequest(meetingID: String, requesterID: String) extends InMessage
case class SetActivePresentationRequest(meetingID: String, requesterID: String, presentationID: String, numPages: Int) extends InMessage
case class EnableWhiteboardRequest(meetingID: String, requesterID: String, enable: Boolean) extends InMessage
case class IsWhiteboardEnabledRequest(meetingID: String, requesterID: String) extends InMessage

