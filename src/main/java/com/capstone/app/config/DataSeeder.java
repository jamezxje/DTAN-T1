package com.capstone.app.config;

import com.capstone.app.entity.*;
import com.capstone.app.entity.constants.MessageConstant;
import com.capstone.app.entity.dto.front.request.BookingRequestDTO;
import com.capstone.app.entity.type.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class DataSeeder {

    public static final List<SystemOption> SYSTEM_OPTION_SEED = List.of(
			new SystemOption("System balance", "system_balance", "0.0"),
			new SystemOption("System deposit out", "system_deposit", "0.0"),
			new SystemOption("System name", "system_name", "Car Rental System"),
            new SystemOption("System Introduction", "system_introduction", "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old."),
            new SystemOption("System email", "system_email", "admin@carrental.com"),
            new SystemOption("System contact", "system_contact", "0123456789"),
            new SystemOption("System address", "system_address", "Hanoi, Vietnam"),
            new SystemOption("System footer text", "system_footer", "© 2021 Car Rental System. All rights reserved.")
    );

    private static PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public static final List<Image> IMAGES_SEED = List.of(
            new Image(1, "7-series-exterior-right-front-three-quarter-3.webp", ImageType.WEBP, "uploads/7-series-exterior-right-front-three-quarter-3.webp", "img1_alt"),
            new Image(2, "7-series-exterior-right-side-view.webp", ImageType.WEBP, "uploads/7-series-exterior-right-side-view.webp", "img2_alt"),
            new Image(3, "new-7-series-exterior-rear-view.webp", ImageType.WEBP, "uploads/new-7-series-exterior-rear-view.webp", "img3_alt"),
            new Image(4, "new-7-series-exterior-right-front-three-quarter.webp", ImageType.WEBP, "uploads/new-7-series-exterior-right-front-three-quarter.webp", "img4_alt"),
            new Image(5, "fronx-exterior-right-front-three-quarter-109.webp", ImageType.WEBP, "uploads/fronx-exterior-right-front-three-quarter-109.webp", "img4_alt"),
            new Image(6, "new-7-series-exterior-right-rear-three-quarter.webp", ImageType.WEBP, "uploads/new-7-series-exterior-right-rear-three-quarter.webp", "img5_alt"),
            new Image(7, "s-class-exterior-right-front-three-quarter-8.webp", ImageType.WEBP, "uploads/s-class-exterior-right-front-three-quarter-8.webp", "img6_alt"),
            new Image(8, "s-class-exterior-right-front-three-quarter-9.webp", ImageType.WEBP, "uploads/s-class-exterior-right-front-three-quarter-9.webp", "img7_alt"),
            new Image(9, "Hyundai-Grand-i10-Nios-200120231541.jpg", ImageType.WEBP, "uploads/Hyundai-Grand-i10-Nios-200120231541.jpg", "img alt 9"),
            new Image(10, "s-class-exterior-right-rear-three-quarter-2.webp", ImageType.WEBP, "uploads/s-class-exterior-right-rear-three-quarter-2.webp", "Rs. 1.82 - 1.85 Crore"),
            new Image(11, "s-class-exterior-right-rear-three-quarter-3.webp", ImageType.WEBP, "uploads/s-class-exterior-right-rear-three-quarter-3.webp", "Rs. 1.82 - 1.85 Crore"),
            new Image(12, "s-class-exterior-right-rear-three-quarter-4.webp", ImageType.WEBP, "uploads/s-class-exterior-right-rear-three-quarter-4.webp", "Rs. 1.82 - 1.85 Crore"),
            new Image(13, "s-class-exterior-right-rear-three-quarter-5.webp", ImageType.WEBP, "uploads/s-class-exterior-right-rear-three-quarter-5.webp", "Rs. 1.82 - 1.85 Crore"),
            new Image(14, "audi.jpeg", ImageType.JPEG, "uploads/audi.jpeg", "audi"),
            new Image(15, "front_1.webp", ImageType.WEBP, "uploads/front_1.webp", "front"),
            new Image(16, "interior_1.jpeg", ImageType.JPEG, "uploads/interior_1.jpeg", "interior"),
            new Image(17, "interior_2.jpeg", ImageType.JPEG, "uploads/interior_2.jpeg", "interior"),
            new Image(18, "jeep_1.jpeg", ImageType.JPEG, "uploads/jeep_1.jpeg", "jeep"),
            new Image(19, "mercedes_1.jpeg", ImageType.JPEG, "uploads/mercedes_1.jpeg", "mercedes"),
            new Image(20, "mercedes_2.jpeg", ImageType.JPEG, "uploads/mercedes_2.jpeg", "mercedes"),
            new Image(21, "range_1.jpeg", ImageType.JPEG, "uploads/range_1.jpeg", "range_rover"),
            new Image(22, "rear_1.jpeg", ImageType.JPEG, "uploads/rear_1.jpeg", "rear"),
            new Image(23, "unknown_car_1.webp", ImageType.WEBP, "uploads/unknown_car_1.webp", "ancient car"),
            new Image(24, "bmw_1.jpeg", ImageType.JPEG, "uploads/bmw_1.jpeg", "bmw car"),
            new Image(25, "bmw_2.jpeg", ImageType.JPEG, "uploads/bmw_2.jpeg", "bmw car")
    );

    public static final List<Brand> BRANDS_SEED = List.of(
            new Brand(1, "Toyota", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Praesentium at dolorem quidem modi. Nam sequi consequatur obcaecati excepturi alias magni, accusamus eius blanditiis delectus ipsam minima ea iste laborum vero?", IMAGES_SEED.get(8)),
            new Brand(2, "Honda", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga.", IMAGES_SEED.get(2)),
            new Brand(3, "Mercedes", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", IMAGES_SEED.get(6)),
            new Brand(4, "Ford", "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.", IMAGES_SEED.get(4))
    );

    public static final List<Member> MEMBERS_SEED = List.of(
            new Member(1,  null, null, "0123456789", "Admin",
                    MemberRole.ROLE_ADMIN, 0.0, LocalDate.now(), "admin@gmail.com",
                    "0123456789", "Vietnam", "admin", getPasswordEncoder().encode("123456"),
                    true, true, LocalDateTime.now(), LocalDateTime.now()),
            new Member(2,  null, null, "0256321686", "Adam Smith",
                    MemberRole.ROLE_OWNER, 500.0, LocalDate.now(), "carowner@gmail.com",
                    "02839284751", "Da nang, Vietnam", "owner", getPasswordEncoder().encode("123456"),
                    true, true, LocalDateTime.now(), LocalDateTime.now()),
            new Member(3,  null, null, "0453378645", "John Doe",
                    MemberRole.ROLE_CUSTOMER, 80.0, LocalDate.now(), "customer@gmail.com",
                    "0978564321", "Phu Tho, Vietnam", "customer", getPasswordEncoder().encode("123456"),
                    true, true, LocalDateTime.now(), LocalDateTime.now()),
            new Member(4,  null, null, "9321872412", "Mark Wilson",
                    MemberRole.ROLE_OWNER, 1000.0, LocalDate.now(), "carowner2@gmail.com",
                    "0987654321", "Vietnam", "owner2", getPasswordEncoder().encode("123456"),
                    true, true, LocalDateTime.now(), LocalDateTime.now()),
            new Member(5,  null, null, "0453444644", "Selena Gomez",
                    MemberRole.ROLE_CUSTOMER, 5000.0, LocalDate.now(), "customer2@gmail.com",
                    null, "Hanoi, Vietnam", "customer2", getPasswordEncoder().encode("123456"),
                    true, true, LocalDateTime.now(), LocalDateTime.now())
    );

    public static final List<Post> POSTS_SEED = List.of(
            new Post(1, "BMW 7 Series: Why do we use it?", "bmw-7-series-why-do-we-use-it", "BMW 7 Series is a full-size luxury sedan produced by the German automaker BMW. It is the successor to the BMW E3 \"New Six\" sedan and is currently in its sixth generation.", null,  PostStatus.PUBLISH, MEMBERS_SEED.get(0),LocalDateTime.now(), LocalDateTime.now(), null),
            new Post(2, "Lorem ipsum sit amet", "lorem-ipsum-sit-amet", "BMW 7 Series is a full-size luxury sedan produced by the German automaker BMW. It is the successor to the BMW E3 \"New Six\" sedan and is currently in its sixth generation.", null,  PostStatus.DRAFT, MEMBERS_SEED.get(0), LocalDateTime.now(), LocalDateTime.now(), null),
            new Post(3, "Is it BMW 7 one of the best car of BMW?", "is-it-bmw-7-one-of-the-best-car-of-bmw", "BMW 7 Series is a full-size luxury sedan produced by the German automaker BMW. It is the successor to the BMW E3 \"New Six\" sedan and is currently in its sixth generation.", null,  PostStatus.PUBLISH, MEMBERS_SEED.get(0),LocalDateTime.now().minusDays(2), LocalDateTime.now().minusDays(1), null),
            new Post(4, "Which is the best for you", "which-is-the-best-for-you", "BMW 7 Series is a full-size luxury sedan produced by the German automaker BMW. It is the successor to the BMW E3 \"New Six\" sedan and is currently in its sixth generation.", null,  PostStatus.TRASH, MEMBERS_SEED.get(0),LocalDateTime.now().minusDays(2), LocalDateTime.now().minusDays(1), null)
    );

    public static final List<Car> CARS_SEED = List.of(
            new Car(1, MEMBERS_SEED.get(3), BRANDS_SEED.get(0), IMAGES_SEED.get(0), List.of(IMAGES_SEED.get(3), IMAGES_SEED.get(10), IMAGES_SEED.get(14), IMAGES_SEED.get(19)),
                    "Mercedes-Benz S-Class 2022", "29T156789",
                    "Black", 4, 2024, TransmissionType.AUTOMATIC, FuelType.DIESEL, 0,
                    "25km/l", 320.0, 0.0, "1323 Delaware Street, Washington, US", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
                    null, "Term of use", false, Status.APPROVED),
            new Car(2, MEMBERS_SEED.get(3), BRANDS_SEED.get(1), IMAGES_SEED.get(1), List.of(IMAGES_SEED.get(6), IMAGES_SEED.get(7)), "BMW 7 Series", "99X531265",
                    "White", 4, 2023, TransmissionType.MANUAL, FuelType.PETROL, 0,
                    "30km/l", 300.0, 0.0, "109 Bay Minette Street, Arizona, US", "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.",
                    null, "Term of use", false, Status.PENDING),
            new Car(3, MEMBERS_SEED.get(1), BRANDS_SEED.get(2), IMAGES_SEED.get(2), List.of(IMAGES_SEED.get(5)), "Mazda 3 2023", "16T156789",
                    "Red", 4, 2022, TransmissionType.AUTOMATIC, FuelType.DIESEL, 0,
                    "25km/l", 400.0, 0.0, "705 Indianapolis Street, Alaska, US", "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?",
                    null, "Term of use", false, Status.REJECTED),
            new Car(4, MEMBERS_SEED.get(1), BRANDS_SEED.get(1), IMAGES_SEED.get(4), List.of(), "Audi A8 L", "15T38888",
                    "Blue", 4, 2021, TransmissionType.MANUAL, FuelType.PETROL, 0,
                    "28km/l", 280.0, 0.0, "11Hulk Street, Arizona, US", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.\" 1914 translation by H. Rackham \"On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains.",
                    null, "Term of use", false, Status.APPROVED),
            new Car(5, MEMBERS_SEED.get(3), BRANDS_SEED.get(3), IMAGES_SEED.get(9), List.of(IMAGES_SEED.get(8)), "Ford Mustang 2008", "17T156789",
                    "Blue", 4, 2021, TransmissionType.AUTOMATIC, FuelType.DIESEL, 0,
                    "25km/l", 280.0, 0.0, "109 Flippin Street, Arizona, US", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.\" 1914 translation by H. Rackham \"On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains.",
                    null, "Term of use", false, Status.APPROVED),
            new Car(6, MEMBERS_SEED.get(1), BRANDS_SEED.get(0), IMAGES_SEED.get(24), List.of(), "Bentley Continental", "17T156789",
                    "Blue", 4, 2021, TransmissionType.AUTOMATIC, FuelType.DIESEL, 0,
                    "25km/l", 280.0, 0.0, "109 Flippin Street, Arizona, US", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.\" 1914 translation by H. Rackham \"On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains.",
                    null, "Term of use", false, Status.APPROVED),
            new Car(7, MEMBERS_SEED.get(1), BRANDS_SEED.get(0), IMAGES_SEED.get(16), List.of(IMAGES_SEED.get(20)), "Bentley Bentayga", "17T156789",
                    "Blue", 4, 2021, TransmissionType.AUTOMATIC, FuelType.DIESEL, 0,
                    "25km/l", 280.0, 0.0, "109 Flippin Street, Arizona, US", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.\" 1914 translation by H. Rackham \"On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains.",
                    null, "Term of use", false, Status.APPROVED),
            new Car(8, MEMBERS_SEED.get(1), BRANDS_SEED.get(1), IMAGES_SEED.get(22), List.of(IMAGES_SEED.get(15)), "Bentayga EWB Range", "17T156789",
                    "Blue", 4, 2021, TransmissionType.AUTOMATIC, FuelType.DIESEL, 0,
                    "25km/l", 280.0, 0.0, "109 Flippin Street, Arizona, US", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.\" 1914 translation by H. Rackham \"On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains.",
                    null, "Term of use", false, Status.APPROVED),
            new Car(9, MEMBERS_SEED.get(1), BRANDS_SEED.get(0), IMAGES_SEED.get(17), List.of(IMAGES_SEED.get(12)), "Ford F-150 2023", "17T156789",
                    "Blue", 4, 2021, TransmissionType.AUTOMATIC, FuelType.DIESEL, 0,
                    "25km/l", 280.0, 0.0, "109 Flippin Street, Arizona, US", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.\" 1914 translation by H. Rackham \"On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains.",
                    null, "Term of use", false, Status.APPROVED),
            new Car(10, MEMBERS_SEED.get(1), BRANDS_SEED.get(0), IMAGES_SEED.get(18), List.of(IMAGES_SEED.get(13)), "Ford Escape Crossover 2024", "17T156789",
                    "Blue", 4, 2021, TransmissionType.AUTOMATIC, FuelType.DIESEL, 0,
                    "25km/l", 280.0, 0.0, "109 Flippin Street, Arizona, US", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.\" 1914 translation by H. Rackham \"On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains.",
                    null, "Term of use", false, Status.APPROVED)
    );

    public static final List<Transaction> DEPOSIT_REQUESTS_SEED = List.of(
            new Transaction(2, MEMBERS_SEED.get(2), 1000.0, LocalDateTime.now().minusDays(5).minusHours(9),TransactionType.REQUEST_IN, TransactionStatus.PENDING, MessageConstant.DEPOSIT_REQUEST),
            new Transaction(2, MEMBERS_SEED.get(2), 8800.0, LocalDateTime.now().minusDays(80),TransactionType.REQUEST_IN, TransactionStatus.PENDING, MessageConstant.DEPOSIT_REQUEST),
            new Transaction(3, MEMBERS_SEED.get(2), 3200.5, LocalDateTime.now().minusDays(100).minusHours(4),TransactionType.REQUEST_IN, TransactionStatus.PENDING, MessageConstant.DEPOSIT_REQUEST),
            new Transaction(3, MEMBERS_SEED.get(2), 1500.0, LocalDateTime.now().minusDays(22).minusHours(22),TransactionType.REQUEST_IN, TransactionStatus.PENDING, MessageConstant.DEPOSIT_REQUEST),
            new Transaction(3, MEMBERS_SEED.get(2), 4400.0, LocalDateTime.now().minusDays(22).minusHours(8),TransactionType.REQUEST_IN, TransactionStatus.PENDING, MessageConstant.DEPOSIT_REQUEST),
            new Transaction(3, MEMBERS_SEED.get(2), 1200.0, LocalDateTime.now().minusDays(18).minusHours(2),TransactionType.REQUEST_IN, TransactionStatus.PENDING, MessageConstant.DEPOSIT_REQUEST),
            new Transaction(4, MEMBERS_SEED.get(2), 7100.0, LocalDateTime.now().minusDays(6).minusHours(6),TransactionType.REQUEST_IN, TransactionStatus.PENDING, MessageConstant.DEPOSIT_REQUEST),
            new Transaction(4, MEMBERS_SEED.get(2), 2000.0, LocalDateTime.now().minusDays(10).minusHours(12),TransactionType.REQUEST_IN, TransactionStatus.PENDING, MessageConstant.DEPOSIT_REQUEST)
    );

    public static final List<Feedback> FEEDBACKS_SEED = List.of(
            new Feedback(1, 4, "This car is freakin' good", LocalDateTime.now(), null),
            new Feedback(2, 2, "This car is freakin' good", LocalDateTime.now(), null),
            new Feedback(3, 1, "This car is freakin' good", LocalDateTime.now(), null),
            new Feedback(4, 5, "This car is freakin' good", LocalDateTime.now(), null),
            new Feedback(5, 3, "This car is freakin' good", LocalDateTime.now(), null),
            new Feedback(6, 4, "This car is freakin' good", LocalDateTime.now(), null),
            new Feedback(7, 2, "This car is freakin' good", LocalDateTime.now(), null),
            new Feedback(8, 1, "This car is freakin' good", LocalDateTime.now(), null),
            new Feedback(9, 1, "This car is freakin' good", LocalDateTime.now(), null),
            new Feedback(10, 5, "This car is freakin' good", LocalDateTime.now(), null)
    );

    public static final List<BookingDetail> BOOKING_DETAILS_SEED = List.of(
            new BookingDetail(null, 100.0, 0.0, PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_HOME, 200.0, "Me Tri,Hanoi, Viet Nam", "Pham Tung Lam", "0332579823475"),
            new BookingDetail(null, 100.0, 0.0, PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_HOME, 200.0, "Me Tri,Hanoi, Viet Nam", "Pham Quang Truong", "0332579823475"),
            new BookingDetail(null, 100.0, 0.0, PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_STORE, 0.0, "Me Tri,Hanoi, Viet Nam", "Lai The Huy", "0332579823475"),
            new BookingDetail(null, 100.0, 0.0, PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_HOME, 200.0, "Me Tri,Hanoi, Viet Nam", "Jessy Pinkman", "0332579823475"),
            new BookingDetail(null, 100.0, 0.0, PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_STORE, 0.0, "Me Tri,Hanoi, Viet Nam", "Jessy White", "0332579823475"),
            new BookingDetail(null, 100.0, 0.0, PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_STORE, 0.0, "Me Tri,Hanoi, Viet Nam", "Skyler Pinkman", "0332579823475"),
            new BookingDetail(null, 100.0, 0.0, PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_HOME, 200.0, "Me Tri,Hanoi, Viet Nam", "Sound Goodmans", "0332579823475"),
            new BookingDetail(null, 100.0, 0.0, PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_HOME, 200.0, "Me Tri,Hanoi, Viet Nam", "Tran the Nhat", "0932479823475"),
            new BookingDetail(null, 100.0, 0.0, PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_STORE, 0.0, "Me Tri,Hanoi, Viet Nam", "Nguyen Quang Kien", "0332579823475"),
            new BookingDetail(null, 100.0, 0.0, PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_HOME, 200.0, "Me Tri,Hanoi, Viet Nam", "Nguyen Van Du", "0332579823475"),
            new BookingDetail(null, 100.0, 0.0, PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_STORE, 0.0, "Me Tri,Hanoi, Viet Nam", "Tran Van", "0332579823475"),
            new BookingDetail(null, 100.0, 0.0, PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_STORE, 0.0, "Me Tri,Hanoi, Viet Nam", "Pham Hieu", "0332579823475"),
            new BookingDetail(null, 100.0, 0.0, PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_HOME, 200.0, "Da Nang, Viet Nam", "Ho Ngoc Hai", "09218398214123"),
            new BookingDetail(null, 100.0, 0.0, PaymentMethod.PAYMENT_ONLINE, PickupType.PICKUP_AT_HOME, 200.0, "Da Nang, Viet Nam", "Pham Minh ngoc", "09218398214123"),
            new BookingDetail(null, 100.0, 0.0, PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_STORE, 0.0, "Da Nang, Viet Nam", "Minh Tran", "09218398214123"),
            new BookingDetail(null, 100.0, 0.0, PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_HOME, 200.0, "Da Nang, Viet Nam", "Que Van Lam", "09218398214123")
    );
    public static final List<Booking> BOOKING_SEED = List.of(
            new Booking(CARS_SEED.get(0), MEMBERS_SEED.get(2), CARS_SEED.get(0).getCarModel(), 500.0, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(10), null, Status.PENDING, false),
            new Booking(CARS_SEED.get(1), MEMBERS_SEED.get(2), CARS_SEED.get(1).getCarModel(), 500.0, LocalDateTime.now().minusDays(5), LocalDateTime.now().minusDays(12), LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(10), Status.DONE, false),
            new Booking(CARS_SEED.get(2), MEMBERS_SEED.get(4), CARS_SEED.get(2).getCarModel(), 500.0, LocalDateTime.now().minusDays(5), LocalDateTime.now().minusDays(9), LocalDateTime.now().minusDays(6), LocalDateTime.now().minusDays(6), Status.DONE, false),
            new Booking(CARS_SEED.get(3), MEMBERS_SEED.get(2), CARS_SEED.get(3).getCarModel(), 500.0, LocalDateTime.now(), LocalDateTime.now().plusDays(12), LocalDateTime.now().plusDays(18), null, Status.APPROVED, false),
            new Booking(CARS_SEED.get(4), MEMBERS_SEED.get(4), CARS_SEED.get(4).getCarModel(), 500.0, LocalDateTime.now(), LocalDateTime.now().plusDays(20), LocalDateTime.now().plusDays(25), null, Status.APPROVED, false),
            new Booking(CARS_SEED.get(5), MEMBERS_SEED.get(2), CARS_SEED.get(5).getCarModel(), 500.0, LocalDateTime.now().minusDays(5), LocalDateTime.now().minusDays(29), LocalDateTime.now().minusDays(26), LocalDateTime.now().minusDays(26), Status.DONE, false),
            new Booking(CARS_SEED.get(6), MEMBERS_SEED.get(4), CARS_SEED.get(6).getCarModel(), 500.0, LocalDateTime.now().minusDays(5), LocalDateTime.now().minusDays(35), LocalDateTime.now().minusDays(30), LocalDateTime.now().minusDays(30), Status.DONE, false),
            new Booking(CARS_SEED.get(7), MEMBERS_SEED.get(4), CARS_SEED.get(7).getCarModel(), 500.0, LocalDateTime.now().minusDays(5), LocalDateTime.now().minusDays(42), LocalDateTime.now().minusDays(36), LocalDateTime.now().minusDays(36), Status.DONE, false),
            new Booking(CARS_SEED.get(8), MEMBERS_SEED.get(4), CARS_SEED.get(8).getCarModel(), 500.0, LocalDateTime.now().minusDays(5), LocalDateTime.now().minusDays(55), LocalDateTime.now().minusDays(43), LocalDateTime.now().minusDays(43), Status.DONE, false),
            new Booking(CARS_SEED.get(9), MEMBERS_SEED.get(2), CARS_SEED.get(9).getCarModel(), 500.0, LocalDateTime.now().minusDays(1), LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(8), null, Status.APPROVED, false),
            new Booking(CARS_SEED.get(9), MEMBERS_SEED.get(4), CARS_SEED.get(9).getCarModel(), 500.0, LocalDateTime.now().minusDays(6), LocalDateTime.now().minusDays(2), LocalDateTime.now().minusDays(2), null, Status.DONE, false),

            new Booking(CARS_SEED.get(0), MEMBERS_SEED.get(2), CARS_SEED.get(0).getCarModel(), 500.0, LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(16), LocalDateTime.now().minusDays(16), Status.DONE, false),
            new Booking(CARS_SEED.get(0), MEMBERS_SEED.get(2), CARS_SEED.get(0).getCarModel(), 500.0, LocalDateTime.now().minusDays(1), LocalDateTime.now().minusHours(8), LocalDateTime.now().plusDays(6), null, Status.APPROVED, false),

            new Booking(CARS_SEED.get(5), MEMBERS_SEED.get(4), CARS_SEED.get(5).getCarModel(), 500.0, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(4), LocalDateTime.now().plusDays(10), null, Status.PENDING, false),
            new Booking(CARS_SEED.get(5), MEMBERS_SEED.get(4), CARS_SEED.get(5).getCarModel(), 500.0, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(6).minusHours(2), LocalDateTime.now().plusDays(12).plusMinutes(199), null, Status.PENDING, false),
            new Booking(CARS_SEED.get(5), MEMBERS_SEED.get(4), CARS_SEED.get(5).getCarModel(), 500.0, LocalDateTime.now().minusDays(2), LocalDateTime.now().plusDays(2).plusMinutes(172), LocalDateTime.now().plusDays(10).plusMinutes(222), null, Status.PENDING, false)
    );

    public static final List<BookingRequestDTO> BOOKING_REQUEST_DTOS_SEED = List.of(
            new BookingRequestDTO(
                    1,"Duong Ngoc Duy", "Hanoi, Viet Nam", "0128391249", LocalDateTime.now().plusDays(16), LocalDateTime.now().plusDays(24).plusHours(6).plusMinutes(22),
                    PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_HOME, 500.0, 200.0, 300.0
            ),
            new BookingRequestDTO(2,
                    "Tran Xuan Duy", "Bacninh", "0974125896", LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(13).plusMinutes(3),
                    PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_STORE, 500.0, 200.0, 300.0
            ),
            new BookingRequestDTO(
                    3,"Lai The Dat", "Hanoi, Viet Nam", "0923772136218", LocalDateTime.now().plusDays(17), LocalDateTime.now().plusDays(22).minusHours(6),
                    PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_HOME, 500.0, 200.0, 300.0
            ),
            new BookingRequestDTO(
                    4,"Pham Quang Hieu", "Hanoi, Viet Nam", "092348723432", LocalDateTime.now().minusHours(10).plusDays(13), LocalDateTime.now().plusDays(22),
                    PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_HOME, 500.0, 200.0, 300.0
            ),
            new BookingRequestDTO(
                    5,"Tuco Goodman", "123 Sherlock Holmes street, New Mexico, UK", "0974125896", LocalDateTime.now().plusDays(8), LocalDateTime.now().plusDays(16),
                    PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_STORE, 500.0, 200.0, 300.0
            ),
            new BookingRequestDTO(
                    6,"Jesse White", "ABC street, New Mexico", "0384762183144", LocalDateTime.now().plusDays(7), LocalDateTime.now().plusDays(12),
                    PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_HOME, 500.0, 200.0, 300.0
            ),
            new BookingRequestDTO(
                    7,"Walter Pinkman", "Bangkok", "0974125896", LocalDateTime.now().minusHours(14).plusDays(6), LocalDateTime.now().plusDays(14),
                    PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_STORE, 500.0, 200.0, 300.0
            ),
            new BookingRequestDTO(
                    8,"Skyler Goodman", "Arizona sky", "8888555521", LocalDateTime.now().plusDays(12), LocalDateTime.now().plusDays(27),
                    PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_HOME, 500.0, 200.0, 300.0
            ),
            new BookingRequestDTO(
                    9,"John H. Joe", "Abq", "0974125896", LocalDateTime.now().minusHours(4).plusDays(3), LocalDateTime.now().plusDays(9),
                    PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_STORE, 500.0, 200.0, 300.0
            ),
            new BookingRequestDTO(
                    10,"Walter Junior", "Laos", "033324412451", LocalDateTime.now().minusHours(11).plusDays(3), LocalDateTime.now().plusDays(8),
                    PaymentMethod.PAYMENT_ON_PICKUP, PickupType.PICKUP_AT_HOME, 500.0, 200.0, 300.0
            )
    );
	
	

}

