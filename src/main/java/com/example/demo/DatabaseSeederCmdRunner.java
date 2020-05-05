package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.Model.Address;
import com.example.demo.Model.Hotel;
import com.example.demo.Model.Review;
import com.example.demo.Repository.HotelRepo;

@Component
public class DatabaseSeederCmdRunner implements CommandLineRunner {
	@Autowired
	private HotelRepo repo;

	@Override
	public void run(String... args) throws Exception {
		Hotel h1 = new Hotel("Ramada Palace", 4000, new Address("Lucknow", "India", 226025),
				Arrays.asList(new Review("Anurag", 8, false), new Review("Rahul", 9, true)));

		Hotel h2 = new Hotel("Trump Towers", 6000, new Address("New Jersey", "USA", 111211),
				Arrays.asList(new Review("Akshtia", 4, true), new Review("Ankita", 9, true)));
		Hotel h3 = new Hotel("Sky Hights", 2300, new Address("Dhaka", "Bangladesh", 900012), new ArrayList());

		Hotel h4 = new Hotel("Surya Hotels", 1000, new Address("Lucknow", "India", 329882),
				Arrays.asList(new Review("Dinesh", 4, true), new Review("Manoj", 8, true)));

		Hotel h5 = new Hotel("Phonix Towers", 2000, new Address("Lucknow", "India", 434323),
				Arrays.asList(new Review("Akshtia", 9, true), new Review("Ankita", 8, true)));

		Hotel h6 = new Hotel("Piccadly Hotels", 1500, new Address("Lucknow", "India", 655566),
				Arrays.asList(new Review("Anurag", 8, false), new Review("Devendra", 7, true)));

		Hotel h7 = new Hotel("The Taj", 2300, new Address("Lucknow", "India", 232323),
				Arrays.asList(new Review("Param", 8, true), new Review("Dev", 4, true)));

		// deleting all the data from database at startup.
		repo.deleteAll();
		System.out.println("data cleared");
		// seeding new data every time at startup

		repo.save(h1);
		repo.save(h2);
		repo.save(h3);
		repo.save(h4);
		repo.save(h5);
		repo.save(h6);
		repo.save(h7);
		System.out.println("data seeded...");
	}

}
