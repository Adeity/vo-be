package cz.cvut.fel.pc2e.garminworker;

public class JsonDataHolder {
	public static String prepareJsonData(String ACCESS_TOKEN_1, String ACCESS_TOKEN_2) {
		return "{\n" +
				"    \"sleeps\": [\n" +
				"        {\n" +
				"            \"awakeDurationInSeconds\": 0,\n" +
				"            \"calendarDate\": \"2021-11-13\",\n" +
				"            \"deepSleepDurationInSeconds\": 350,\n" +
				"            \"durationInSeconds\": 36000,\n" +
				"            \"lightSleepDurationInSeconds\": 22000,\n" +
				"            \"remSleepInSeconds\": 12000,\n" +
				"            \"sleepLevelsMap\": {\n" +
				"                \"deep\": [\n" +
				"                    {\n" +
				"                        \"endTimeInSeconds\": 1636846080,\n" +
				"                        \"startTimeInSeconds\": 1636845780\n" +
				"                    }\n" +
				"                ],\n" +
				"                \"light\": [\n" +
				"                    {\n" +
				"                        \"endTimeInSeconds\": 1636845780,\n" +
				"                        \"startTimeInSeconds\": 1636845000\n" +
				"                    }\n" +
				"                ],\n" +
				"                \"rem\": [\n" +
				"                    {\n" +
				"                        \"endTimeInSeconds\": 1636847160,\n" +
				"                        \"startTimeInSeconds\": 1636846200\n" +
				"                    }\n" +
				"                ]\n" +
				"            },\n" +
				"            \"startTimeInSeconds\": 1636845000,\n" +
				"            \"startTimeOffsetInSeconds\": 3600,\n" +
				"            \"summaryId\": \"x420295e-619045c8-86c4\",\n" +
				"            \"timeOffsetSleepSpo2\": {},\n" +
				"            \"unmeasurableSleepInSeconds\": 840,\n" +
				"            \"userAccessToken\": \"" + ACCESS_TOKEN_1 + "\",\n" +
				"            \"userId\": \"c95aef57-568e-4a00-9a88-ff66c2437d0d\",\n" +
				"            \"validation\": \"ENHANCED_TENTATIVE\"\n" +
				"        },\n" +
				"                {\n" +
				"            \"awakeDurationInSeconds\": 0,\n" +
				"            \"calendarDate\": \"2021-11-13\",\n" +
				"            \"deepSleepDurationInSeconds\": 350,\n" +
				"            \"durationInSeconds\": 36000,\n" +
				"            \"lightSleepDurationInSeconds\": 22000,\n" +
				"            \"remSleepInSeconds\": 12000,\n" +
				"            \"sleepLevelsMap\": {\n" +
				"                \"deep\": [\n" +
				"                    {\n" +
				"                        \"endTimeInSeconds\": 1636846080,\n" +
				"                        \"startTimeInSeconds\": 1636845780\n" +
				"                    }\n" +
				"                ],\n" +
				"                \"light\": [\n" +
				"                    {\n" +
				"                        \"endTimeInSeconds\": 1636845780,\n" +
				"                        \"startTimeInSeconds\": 1636845000\n" +
				"                    }\n" +
				"                ],\n" +
				"                \"rem\": [\n" +
				"                    {\n" +
				"                        \"endTimeInSeconds\": 1636847160,\n" +
				"                        \"startTimeInSeconds\": 1636846200\n" +
				"                    }\n" +
				"                ]\n" +
				"            },\n" +
				"            \"startTimeInSeconds\": 1636845000,\n" +
				"            \"startTimeOffsetInSeconds\": 3600,\n" +
				"            \"summaryId\": \"x420295e-619045c8-86c4\",\n" +
				"            \"timeOffsetSleepSpo2\": {},\n" +
				"            \"unmeasurableSleepInSeconds\": 840,\n" +
				"            \"userAccessToken\": \"" + ACCESS_TOKEN_2 + "\",\n" +
				"            \"userId\": \"c95aef57-568e-4a00-9a88-ff66c2437d0d\",\n" +
				"            \"validation\": \"ENHANCED_TENTATIVE\"\n" +
				"        }\n" +
				"    ]\n" +
				"}";
	}

	public static String prepareInvalidData() {
		return "{\"invalid\": 123}";
	}
}
