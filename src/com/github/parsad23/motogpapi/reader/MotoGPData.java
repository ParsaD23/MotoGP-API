package com.github.parsad23.motogpapi.reader;

import com.github.parsad23.motogpapi.domain.*;
import com.github.parsad23.motogpapi.exceptions.DataNotAvailableException;

import java.io.IOException;
import java.util.List;

/**
 * Reads the data from the website.
 * @since 1.0
 */
public class MotoGPData {

	private final SessionResultsReader sessionResultsReader;
	private final ChampionshipStandingsReader championshipStandingsReader;

	public MotoGPData () throws IOException {
		sessionResultsReader = new SessionResultsReader();
		championshipStandingsReader = new ChampionshipStandingsReader();
	}

	/**
	 * Returns the requested session's results
	 * @param category
	 * @param year
	 * @param raceCode RaceCode. Look at the <a href="https://results.motorsportstats.com/series/motogp">MotorsportStats</a>
	 *                 website to choose the correct raceCode.
	 * @param session
	 * @return List of RiderSession
	 * @throws IOException
	 * @throws DataNotAvailableException
	 * @since 2.0
	 */
	public List<RiderSession> getSessionResults(Category category, int year, String raceCode, Session session) throws IOException, DataNotAvailableException {
		checkParameters(category, year);
		return sessionResultsReader.getSessionResults(category, year, raceCode, -1, session);
	}

	/**
	 * Returns the requested session's results
	 * @param category
	 * @param year
	 * @param raceNumber Number of the race. Starts from 1
	 * @param session
	 * @return List of RiderSession
	 * @throws IOException
	 * @throws DataNotAvailableException
	 * @since 2.2
	 */
	public List<RiderSession> getSessionResults(Category category, int year, int raceNumber, Session session) throws IOException, DataNotAvailableException {
		checkParameters(category, year, raceNumber);
		return sessionResultsReader.getSessionResults(category, year, null, raceNumber, session);
	}

	/**
	 * Returns the riders' standings.
	 * @param category
	 * @param year
	 * @return List of RiderSession
	 * @throws IOException
	 * @throws DataNotAvailableException
	 * @since 2.1
	 */
	public List<RiderStandings> getRidersStandings(Category category, int year) throws IOException, DataNotAvailableException {
		checkParameters(category, year);
		return championshipStandingsReader.getRidersStandings(category, year);
	}

	/**
	 * Returns the constructors' standings.
	 * @param category
	 * @param year
	 * @return List of ConstructorStandings
	 * @throws IOException
	 * @throws DataNotAvailableException
	 * @since 3.0
	 */
	public List<ConstructorStandings> getConstructorsStandings(Category category, int year) throws IOException, DataNotAvailableException {
		checkParameters(category, year);
		return championshipStandingsReader.getConstructorsStandings(category, year);
	}

	/**
	 * Returns the teams' standings.
	 * @param category
	 * @param year
	 * @return List of TeamStandings
	 * @throws IOException
	 * @throws DataNotAvailableException
	 * @since 3.0
	 */
	public List<TeamStandings> getTeamsStandings(Category category, int year) throws IOException, DataNotAvailableException {
		checkParameters(category, year);
		return championshipStandingsReader.getTeamsStandings(category, year);
	}

	/**
	 * Returns the riders' standings after a specific grandprix defined by the raceCode.
	 * @param category
	 * @param year
	 * @param raceCode RaceCode. Look at the <a href="https://results.motorsportstats.com/series/motogp">MotorsportStats</a>
	 *                 website to choose the correct raceCode.
	 * @return List of RiderStandings
	 * @throws IOException
	 * @throws DataNotAvailableException
	 * @since 3.0
	 */
	public List<RiderStandings> getRidersStandings(Category category, int year, String raceCode) throws IOException, DataNotAvailableException {
		checkParameters(category, year);
		return championshipStandingsReader.getRidersStandings(category, year, -1, raceCode);
	}

	/**
	 * Returns the riders' standings after a specific grandprix defined by the raceNumber.
	 * @param category
	 * @param year
	 * @param raceNumber Number of the race. Starts from 1
	 * @return List of RiderStandings
	 * @throws IOException
	 * @throws DataNotAvailableException
	 * @since 3.0
	 */
	public List<RiderStandings> getRidersStandings(Category category, int year, int raceNumber) throws IOException, DataNotAvailableException {
		checkParameters(category, year, raceNumber);
		return championshipStandingsReader.getRidersStandings(category, year, raceNumber, null);
	}

	/**
	 * Returns the constructor' standings after a specific grandprix defined by the raceCode.
	 * @param category
	 * @param year
	 * @param raceCode RaceCode. Look at the <a href="https://results.motorsportstats.com/series/motogp">MotorsportStats</a>
	 *                 website to choose the correct raceCode.
	 * @return List of ConstructorStandings
	 * @throws IOException
	 * @throws DataNotAvailableException
	 * @since 3.0
	 */
	public List<ConstructorStandings> getConstructorsStandings(Category category, int year, String raceCode) throws IOException, DataNotAvailableException {
		checkParameters(category, year);
		return championshipStandingsReader.getConstructorsStandings(category, year, -1, raceCode);
	}

	/**
	 * Returns the constructors' standings after a specific grandprix defined by the raceNumber.
	 * @param category
	 * @param year
	 * @param raceNumber Number of the race. Starts from 1
	 * @return List of ConstructorStandings
	 * @throws IOException
	 * @throws DataNotAvailableException
	 * @since 3.0
	 */
	public List<ConstructorStandings> getConstructorsStandings(Category category, int year, int raceNumber) throws IOException, DataNotAvailableException {
		checkParameters(category, year, raceNumber);
		return championshipStandingsReader.getConstructorsStandings(category, year, raceNumber, null);
	}

	/**
	 *	Returns the teams' standings after a specific grandprix defined by the raceCode.
	 * @param category
	 * @param year
	 * @param raceCode RaceCode. Look at the <a href="https://results.motorsportstats.com/series/motogp">MotorsportStats</a>
	 *                 website to choose the correct raceCode.
	 * @return List of TeamStandings
	 * @throws IOException
	 * @throws DataNotAvailableException
	 * @since 3.0
	 */
	public List<TeamStandings> getTeamsStandings(Category category, int year, String raceCode) throws IOException, DataNotAvailableException {
		checkParameters(category, year);
		return championshipStandingsReader.getTeamsStandings(category, year, -1, raceCode);
	}

	/**
	 * Returns the teams' standings after a specific grandprix defined by the raceNumber.
	 * @param category
	 * @param year
	 * @param raceNumber Number of the race. Starts from 1
	 * @return List of TeamStandings
	 * @throws IOException
	 * @throws DataNotAvailableException
	 * @since 3.0
	 */
	public List<TeamStandings> getTeamsStandings(Category category, int year, int raceNumber) throws IOException, DataNotAvailableException {
		checkParameters(category, year, raceNumber);
		return championshipStandingsReader.getTeamsStandings(category, year, raceNumber, null);
	}

	private void checkParameters(Category category, int year, int raceNumber) throws IOException {
		if (raceNumber < 1)
			throw new IOException("Race number must start from 1");
		checkParameters(category, year);
	}

	private void checkParameters(Category category, int year) throws IOException {
		if (category == Category.MotoGP && year < 1949)
			throw new IOException("The MotoGP/500cc class did not exist before 1949...");
		if (category == Category.Moto2 && year < 2010)
			throw new IOException("The Moto2 class did not exist before 2010...");
		if (category == Category.Moto3 && year < 2012)
			throw new IOException("The Moto3 class did not exist before 2012...");
	}

}
