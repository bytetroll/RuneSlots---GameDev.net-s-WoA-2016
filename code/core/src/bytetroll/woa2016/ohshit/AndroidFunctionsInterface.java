package bytetroll.woa2016.ohshit;

public interface AndroidFunctionsInterface {
	public boolean IsSwarmInitiated();
	public void SwarmInitiate();
	public void SubmitScore(float credits);
	public void ShowLeaderboardSwarm();
	public void SwarmUnlockAchievement(int AchievementID);
	public void SwarmYourGameCloudDataSave(String theYourGameCloudData);
	public void SwarmYourGameCloudDataLoad();
	public String SwarmYourGameCloudDataGet();
	public float getScore();
	public float getReadedBarcode();
	public void readBarCode();
}