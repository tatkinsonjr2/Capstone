package sanctuaryraider.dependency;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

//    DeleteTicketActivity provideDeleteTicketActivity();
//
//    DeleteProjectActivity provideDeleteProjectActivity();
//
//    GetProjectActivity provideGetProjectActivity();
//
//    CreateTicketActivity provideCreateTicketActivity();
//
//    GetTicketActivity provideGetTicketActivity();
//
//    GetAllTicketsActivity provideGetAllTicketsActivity();
//
//    CreateProjectActivity provideCreateProjectActivity();
//
//    UpdateTicketDetailsActivity provideUpdateTicketActivity();
//
//    GetAllProjectsActivity provideGetAllProjectsActivity();
//
//    UpdateProjectStatusActivity provideUpdateProjectStatusActivity();
//
//    UpdateProjectDetailsActivity provideUpdateProjectDetailsActivity();
//
//    UpdateTicketStatusActivity provideUpdateTicketStatusActivity();

}
