package javaproject.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javaproject.dao.ConveyanceDBDao;
import javaproject.dao.ConveyanceRegistryDao;
import javaproject.dao.LocationDBDao;
import javaproject.dao.LocationRegistryDao;
import javaproject.dao.UserAccountDao;
import javaproject.models.ConveyanceDB;
import javaproject.models.ConveyanceRegistry;
import javaproject.models.LocationDB;
import javaproject.models.LocationRegistry;
import javaproject.models.UserAccount;

/**
 * @author Ahmad
 * @version 2.0
 * 
 *          This Controller class includes the mappings for all the Servlets
 *          (jsp's) in the Spring MVC framework. In addition to the mappings, it
 *          also includes 2 loggers (activity logger and DB logger), as well as
 *          two custom exceptions in the form of .jsp pages that the server
 *          redirects to in case of an error.
 */
@Controller
public class AppController {
	public static final Logger logger = LogManager.getLogger("ActLogging"); // allactivity
	public static final Logger DBlogger = LogManager.getLogger("DBLogging"); // DB activity

	@RequestMapping(value = "/") // '/' means call to home page (index.jsp); refers to dispatcher servlet
	public String indexPage() {
		logger.info("Landed in home page");
		return "index";// string index suffixed with .jsp
	}

	@RequestMapping(value = "/register", method = POST)
	public String registerUser(Model model) {
		logger.info("Landed in Register User page");
		return "register";
	}

	@RequestMapping(value = "registerUser", method = POST)
	public void registerUser(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// HttpSession session =request.getSession();
		RequestDispatcher rd;
		UserAccount useraccount = new UserAccount();
		useraccount.setfName(request.getParameter("fName"));
		useraccount.setlName(request.getParameter("lName"));
		useraccount.setuName(request.getParameter("uName"));
		useraccount.setPassword(request.getParameter("pwd"));
		useraccount.setEmail(request.getParameter("email"));
		System.out.println(useraccount);
		try {
			// persist user to database
			UserAccountDao uad = new UserAccountDao();
			uad.addUserAccount(useraccount);

			logger.info("A new User registered. Details " + useraccount);
			DBlogger.info("A new User registered. Details " + useraccount);

			rd = request.getRequestDispatcher("login.jsp");
			// goes to login.jsp afer registering user

		} catch (Exception e) {
			logger.info("A new User did not succeed creation");
			DBlogger.info("A new User did not succeed persistence");
			rd = request.getRequestDispatcher("unsuccess.jsp");
		}
		rd.forward(request, response);
	}

	@RequestMapping(value = "/login", method = POST)
	public String verifyUser(Model model) {
		logger.info("Landed in verify User page");
		return "login";
	}

	@RequestMapping(value = "verifyUser", method = POST)
	public void verifyUser(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		RequestDispatcher reqdispatch = null;
		HttpSession session = request.getSession();// create a session object
		String un = request.getParameter("uName");
		String pwd = request.getParameter("pwd");
		try {
			UserAccountDao uadao = new UserAccountDao();
			UserAccount useraccount = uadao.getUserAccountByUsernamePassword(un, pwd);
			reqdispatch = request.getRequestDispatcher("locationregistry.jsp");
			session.setAttribute("Username", un);// setting the session attribute
			logger.info("User verification successful");

		} catch (Exception e) {
			reqdispatch = request.getRequestDispatcher("unsuccessful_login.jsp");
			logger.info("User verification un-successful");
		}
		reqdispatch.forward(request, response);

	}

	@RequestMapping(value = "/locationregistry", method = POST)
	public String locReg(Model model) {
		logger.info("Landed in location registry page");
		return "locationregistry";
	}

	@RequestMapping(value = "locReg", method = POST)
	public void locReg(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Java_Project");
		EntityManager em = emf.createEntityManager();

		RequestDispatcher rd = null;
		HttpSession session = request.getSession();

		LocationRegistry locreg = new LocationRegistry();
		locreg.setLocationType(request.getParameter("locationType"));
		locreg.setStayDuration(Integer.parseInt(request.getParameter("stayDuration")));
		// check what property type was selected; then forward to appropriate .jsp using
		// request despatcher
		String loctype = request.getParameter("locationType");
		session.setAttribute("loctype", loctype);

		String name = (String) session.getAttribute("Username");
		UserAccount ua = (UserAccount) em.createQuery("select ua from UserAccount ua " + "where ua.uName =:uName")
				.setParameter("uName", name).getSingleResult();

		locreg.setUseraccount(ua);

		em.close();
		// Do we need bidirectional mapping?
		System.out.println(locreg);
		try {
			// persist LocReg to database
			LocationRegistryDao locregdao = new LocationRegistryDao();
			locregdao.addLocRegistry(locreg);

			logger.info("A new LocationRegistry added. User Account: " + locreg.getUseraccount() + "Location Type: "
					+ locreg.getLocationType() + " Stay Duration: " + locreg.getStayDuration());

			DBlogger.info("A new LocationRegistry persisted. User Account: " + locreg.getUseraccount()
					+ "Location Type: " + locreg.getLocationType() + " Stay Duration: " + locreg.getStayDuration());

			System.out.println("Location Registry Added");

			// primary key mapping
			int locregpk = locreg.getLocationReg();
			session.setAttribute("locregpk", locregpk);

			if (loctype.equals("BeachFront")) {
				rd = request.getRequestDispatcher("beachproperties.jsp"); // goes to next jsp page
			}

			else if (loctype.equals("HillSide")) {
				rd = request.getRequestDispatcher("hillproperties.jsp");

			}

		} catch (Exception e) {
			logger.info("A new LocationRegistry failed to register");
			DBlogger.info("A new LocationRegistry failed to persist");
			rd = request.getRequestDispatcher("unsuccess.jsp");
		}
		rd.forward(request, response);
	}

	@RequestMapping(value = "/beachproperties", method = POST)
	public String beachproperty(Model model) {
		logger.info("Landed in Beach property selection page");
		return "beachproperties";
	}

	@RequestMapping(value = "beachproperty", method = POST)
	public void beachproperty(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Java_Project");
		EntityManager em = emf.createEntityManager();

		RequestDispatcher rd = null;
		HttpSession session = request.getSession();

		LocationDB locDB = new LocationDB();

		locDB.setPropertyName(request.getParameter("propertyName"));
		locDB.setPropertyPrice(Double.parseDouble(request.getParameter("propertyPrice")));

		int pk = (int) session.getAttribute("locregpk");

		LocationRegistry locreg = (LocationRegistry) em
				.createQuery("select lr from LocationRegistry lr " + "where lr.locationReg =:locationReg")
				.setParameter("locationReg", pk).getSingleResult();

		locDB.setLocationregistry(locreg);
		// One to many mapping between LocationRegistry and Location DB
		em.close();
		try {
			LocationDBDao locDBdao = new LocationDBDao();
			locDBdao.addLocDB(locDB);

			logger.info("A new Location Database record added. Loc Reg.: " + locDB.getLocationregistry()
					+ " Property Name: " + locDB.getPropertyName() + " Property Price: " + locDB.getPropertyPrice());

			DBlogger.info("A new Location Database record persisted. Loc Reg.: " + locDB.getLocationregistry()
					+ " Property Name: " + locDB.getPropertyName() + " Property Price: " + locDB.getPropertyPrice());

			System.out.println("Location DB Added");

			int locdbpk = locDB.getLocationID();
			session.setAttribute("locdbpk", locdbpk);

			rd = request.getRequestDispatcher("displayBooking");
			// Add conveyance page
		} catch (Exception e) {
			rd = request.getRequestDispatcher("unsuccess.jsp");
			logger.info("A new Location DB record failed to register");
			DBlogger.info("A new Location DB record failed to persist");
		}
		rd.forward(request, response);
	}

	@RequestMapping(value = "/hillproperties", method = POST)
	public String hillproperty(Model model) {
		logger.info("Landed in Hill property selection page");
		return "hillproperties";
	}

	@RequestMapping(value = "hillproperty", method = POST)
	public void hillproperty(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Java_Project");
		EntityManager em = emf.createEntityManager();

		RequestDispatcher rd = null;
		HttpSession session = request.getSession();

		LocationDB locDB = new LocationDB();

		locDB.setPropertyName(request.getParameter("propertyName"));
		locDB.setPropertyPrice(Double.parseDouble(request.getParameter("propertyPrice")));

		int pk = (int) session.getAttribute("locregpk");

		LocationRegistry locreg = (LocationRegistry) em
				.createQuery("select lr from LocationRegistry lr " + "where lr.locationReg =:locationReg")
				.setParameter("locationReg", pk).getSingleResult();

		locDB.setLocationregistry(locreg);
		// One to many mapping between LocationRegistry and Location DB
		em.close();
		try {
			LocationDBDao locDBdao = new LocationDBDao();
			locDBdao.addLocDB(locDB);

			logger.info("A new Location Database record added. Loc Reg.: " + locDB.getLocationregistry()
					+ " Property Name: " + locDB.getPropertyName() + " Property Price: " + locDB.getPropertyPrice());

			DBlogger.info("A new Location Database record persisted. Loc Reg.: " + locDB.getLocationregistry()
					+ " Property Name: " + locDB.getPropertyName() + " Property Price: " + locDB.getPropertyPrice());

			System.out.println("Location DB Added");

			int locdbpk = locDB.getLocationID();
			session.setAttribute("locdbpk", locdbpk);

			rd = request.getRequestDispatcher("displayBooking");
			// Add conveyance page
		} catch (Exception e) {
			rd = request.getRequestDispatcher("unsuccess.jsp");
			logger.info("A new Location DB record failed to register");
			DBlogger.info("A new Location DB record failed to persist");
		}
		rd.forward(request, response);
	}

	// Create Model&View; Goes to landing page hotel via Model&View
	@RequestMapping(value = "/displayBooking")
	public ModelAndView seeBooking(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		// Map <String,Object> model = new HashMap <String, Object>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Java_Project");
		EntityManager em = emf.createEntityManager();

		HttpSession session = request.getSession();
		int key = (int) session.getAttribute("locdbpk");
		System.out.println(key);

		LocationDB locdbout = new LocationDB();

		locdbout = (LocationDB) em.createQuery("SELECT record from LocationDB record WHERE record.locationID LIKE ?1")
				.setParameter(1, key).getSingleResult();

		System.out.println(locdbout);
		// model.put("BookingInfo", locdbout); - Multiple objects

		mv.addObject("BookingInfo", locdbout);
		mv.setViewName("landingpagehotel");

		return mv;

	}

	// mapping for jsp
	@RequestMapping(value = "/landingpagehotel", method = POST)
	public String landinghotel(Model model) {
		logger.info("Landed in Hotel details page");
		return "landingpagehotel";
	}

	@RequestMapping(value = "/conveyanceregistry", method = POST)
	public String convReg(Model model) {
		logger.info("Landed in Conveyance Registry page");
		return "conveyanceregistry";
	}

	@RequestMapping(value = "convReg", method = POST)
	public void convReg(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Java_Project");
		EntityManager em = emf.createEntityManager();

		RequestDispatcher rd = null;
		HttpSession session = request.getSession();

		ConveyanceRegistry convreg = new ConveyanceRegistry();
		convreg.setConvType(request.getParameter("convType"));

		// check what conv. type was selected; then forward to appropriate .jsp

		String convtype = request.getParameter("convType");
		session.setAttribute("convtype", convtype);

		String name = (String) session.getAttribute("Username");
		UserAccount ua = (UserAccount) em.createQuery("select ua from UserAccount ua " + "where ua.uName =:uName")
				.setParameter("uName", name).getSingleResult();

		convreg.setUseraccount(ua);

		em.close();
		// Do we need bidirectional mapping?
		System.out.println(convreg);
		try {
			// persist convreg to database
			ConveyanceRegistryDao convregdao = new ConveyanceRegistryDao();
			convregdao.addConvRegistry(convreg);

			logger.info("A new Conveyance Registry added. User Account: " + convreg.getUseraccount()
					+ "Conveyance Type: " + convreg.getConvType());

			DBlogger.info("A new Conveyance Registry persisted. User Account: " + convreg.getUseraccount()
					+ "Conveyance Type: " + convreg.getConvType());

			System.out.println("Location Registry Added");

			// primary key mapping
			int convregpk = convreg.getConvReg();
			session.setAttribute("convregpk", convregpk);

			if (convtype.equals("Flight")) {
				rd = request.getRequestDispatcher("flights.jsp"); // goes to next jsp page
			}

			else if (convtype.equals("Train")) {
				rd = request.getRequestDispatcher("trains.jsp");

			}

		} catch (Exception e) {
			logger.info("A new Conveyance Registry record failed to register");
			DBlogger.info("A new Conveyance Registry record failed to persist");
			rd = request.getRequestDispatcher("unsuccess.jsp");
		}
		rd.forward(request, response);
	}

	@RequestMapping(value = "/flights", method = POST)
	public String flightInfo(Model model) {
		logger.info("Landed in flight selection page");
		return "flights";
	}

	@RequestMapping(value = "flightInfo", method = POST)
	public void flightInfo(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Java_Project");
		EntityManager em = emf.createEntityManager();

		RequestDispatcher rd = null;
		HttpSession session = request.getSession();

		ConveyanceDB convdb = new ConveyanceDB();

		convdb.setConvName(request.getParameter("flightName"));
		convdb.setConvPrice(Double.parseDouble(request.getParameter("flightPrice")));

		int pk = (int) session.getAttribute("convregpk");

		ConveyanceRegistry convreg = (ConveyanceRegistry) em
				.createQuery("select cr from ConveyanceRegistry cr " + "where cr.convReg =:convReg")
				.setParameter("convReg", pk).getSingleResult();

		convdb.setConveyanceregistry(convreg);
		// One to many mapping between LocationRegistry and Location DB
		em.close();
		try {
			ConveyanceDBDao convDBdao = new ConveyanceDBDao();
			convDBdao.addConvDB(convdb);

			logger.info("A new Conveyance DB record added. Conv Reg.: " + convdb.getConveyanceregistry()
					+ " Conveyance Name: " + convdb.getConvName() + " Conveyance Price: " + convdb.getConvPrice());

			DBlogger.info("A new Conveyance DB record persisted. Conv Reg.: " + convdb.getConveyanceregistry()
					+ " Conveyance Name: " + convdb.getConvName() + " Conveyance Price: " + convdb.getConvPrice());

			System.out.println("Conveyance DB Added");

			int convdbpk = convdb.getConvID();
			session.setAttribute("convdbpk", convdbpk);

			rd = request.getRequestDispatcher("displayTravel");
			// Add conveyance page
		} catch (Exception e) {

			logger.info("A new Conveyance DB record failed to register");
			DBlogger.info("A new Conveyance DB record failed to persist");
			rd = request.getRequestDispatcher("unsuccess.jsp");
		}
		rd.forward(request, response);
	}

	@RequestMapping(value = "/trains", method = POST)
	public String trainInfo(Model model) {
		logger.info("Landed in Train selection page");
		return "trains";
	}

	@RequestMapping(value = "trainInfo", method = POST)
	public void trainInfo(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Java_Project");
		EntityManager em = emf.createEntityManager();

		RequestDispatcher rd = null;
		HttpSession session = request.getSession();

		ConveyanceDB convdb = new ConveyanceDB();

		convdb.setConvName(request.getParameter("trainName"));
		convdb.setConvPrice(Double.parseDouble(request.getParameter("trainPrice")));

		int pk = (int) session.getAttribute("convregpk");

		ConveyanceRegistry convreg = (ConveyanceRegistry) em
				.createQuery("select cr from ConveyanceRegistry cr " + "where cr.convReg =:convReg")
				.setParameter("convReg", pk).getSingleResult();

		convdb.setConveyanceregistry(convreg);
		// One to many mapping between LocationRegistry and Location DB
		em.close();
		try {
			ConveyanceDBDao convDBdao = new ConveyanceDBDao();
			convDBdao.addConvDB(convdb);

			logger.info("A new Conveyance DB record added. Conv Reg.: " + convdb.getConveyanceregistry()
					+ " Conveyance Name: " + convdb.getConvName() + " Conveyance Price: " + convdb.getConvPrice());

			DBlogger.info("A new Conveyance DB record persisted. Conv Reg.: " + convdb.getConveyanceregistry()
					+ " Conveyance Name: " + convdb.getConvName() + " Conveyance Price: " + convdb.getConvPrice());

			System.out.println("Conveyance DB Added");

			int convdbpk = convdb.getConvID();
			session.setAttribute("convdbpk", convdbpk);

			rd = request.getRequestDispatcher("displayTravel");
			// Add conveyance page
		} catch (Exception e) {
			logger.info("A new Conveyance DB record failed to register");
			DBlogger.info("A new Conveyance DB record failed to persist");
			rd = request.getRequestDispatcher("unsuccess.jsp");

		}
		rd.forward(request, response);
	}

	// Create Model&View; Goes to landingpagetravel via Model&View
	@RequestMapping(value = "/displayTravel")
	public ModelAndView seeTravel(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		// Map <String,Object> model = new HashMap <String, Object>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Java_Project");
		EntityManager em = emf.createEntityManager();

		HttpSession session = request.getSession();
		int key = (int) session.getAttribute("convdbpk");
		System.out.println(key);

		ConveyanceDB convdbout = new ConveyanceDB();

		convdbout = (ConveyanceDB) em.createQuery("SELECT record from ConveyanceDB record WHERE record.convID LIKE ?1")
				.setParameter(1, key).getSingleResult();

		System.out.println(convdbout);
		// model.put("BookingInfo", locdbout); - Multiple objects

		mv.addObject("TravelInfo", convdbout);
		mv.setViewName("landingpagetravel");

		return mv;

	}

	// mapping for jsp
	@RequestMapping(value = "/landingpagetravel", method = POST)
	public String landingtravel(Model model) {
		logger.info("Landed in Travel details page");
		return "landingpagetravel";
	}

	@RequestMapping(value = "/checkout")
	public ModelAndView seeAllDetails(HttpServletRequest request, HttpServletResponse response) {
		// ModelAndView mv = new ModelAndView();

		Map<String, Object> info = new HashMap<String, Object>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Java_Project");
		EntityManager em = emf.createEntityManager();

		HttpSession session = request.getSession();

		int keyloc1 = (int) session.getAttribute("locregpk");
		int keyloc2 = (int) session.getAttribute("locdbpk");

		int keycv1 = (int) session.getAttribute("convregpk");
		int keycv2 = (int) session.getAttribute("convdbpk");

		LocationRegistry locreg = new LocationRegistry();
		LocationDB locdb = new LocationDB();

		ConveyanceRegistry convreg = new ConveyanceRegistry();
		ConveyanceDB convdbout = new ConveyanceDB();

		try {
			locreg = (LocationRegistry) em
					.createQuery("SELECT record from LocationRegistry record WHERE record.locationReg LIKE ?1")
					.setParameter(1, keyloc1).getSingleResult();

			locdb = (LocationDB) em.createQuery("SELECT record from LocationDB record WHERE record.locationID LIKE ?1")
					.setParameter(1, keyloc2).getSingleResult();

			convreg = (ConveyanceRegistry) em
					.createQuery("SELECT record from ConveyanceRegistry record WHERE record.convReg LIKE ?1")
					.setParameter(1, keycv1).getSingleResult();

			convdbout = (ConveyanceDB) em
					.createQuery("SELECT record from ConveyanceDB record WHERE record.convID LIKE ?1")
					.setParameter(1, keycv2).getSingleResult();

			logger.info("Information retrieval successful");
			DBlogger.info("Information retrieval successful");

		} catch (Exception e) {
			logger.info("Information retrieval un-successful");
			DBlogger.info("Information retrieval un-successful");
		}
		em.close();

		info.put("LocReg", locreg);
		info.put("LocDB", locdb);

		info.put("ConvReg", convreg);
		info.put("ConvDB", convdbout);

		return new ModelAndView("checkoutpage", "info", info);

	}

	// mapping for jsp
	@RequestMapping(value = "/checkoutpage", method = POST)
	public String checkout(Model model) {
		logger.info("Landed in Checkout page");
		return "checkoutpage";
	}

	@RequestMapping(value = "/payment", method = POST)
	public String payportal(Model model) {
		logger.info("Landed in Payment portal page");
		return "payment";
	}

	// Custom exceptions
	@RequestMapping(value = "/unsuccessful_login", method = POST)
	public String custom_exception1(Model model) {
		logger.info("Landed in unsuccessful login page");
		return "unsuccessful_login";
	}

	// Custom exceptions
	@RequestMapping(value = "/unsuccess", method = POST)
	public String custom_exception2(Model model) {
		logger.info("Landed in unsuccessful login page");
		return "unsuccess";
	}

}
