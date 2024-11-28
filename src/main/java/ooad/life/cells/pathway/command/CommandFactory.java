package ooad.life.cells.pathway.command;

import ooad.life.cells.pathway.Subject;
import ooad.life.cells.pathway.SubjectRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandFactory {

    private final SubjectRegistry subjectRegistry;


    @Autowired
    public CommandFactory(SubjectRegistry subjectRegistry) {
        this.subjectRegistry = subjectRegistry;
    }

    public <T,U> BindCommand <T,U> createBindCommand(T key, U value, SubjectRegistry subjectRegistry){
        return new BindCommand<>(key, value, subjectRegistry);
    }

    public <T,U> PhosphorylateCommand <T,U> createPhoshorylatecommand(T key, U value, SubjectRegistry subjectRegistry)
    {
        return new PhosphorylateCommand<>(key, value, subjectRegistry);
    }

    public <T> DephosphorylateCommand <T> createDephoshorylatecommand(T key, SubjectRegistry subjectRegistry)
    {
        return new DephosphorylateCommand<>(key, subjectRegistry);
    }

    public <T, U> RegulateCommand <T, U> createRegulateCommand(T key, U value, SubjectRegistry subjectRegistry)
    {
        return new RegulateCommand<>(key, value, subjectRegistry);
    }
}
