import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { FilesInfosUpdateComponent } from 'app/entities/files-infos/files-infos-update.component';
import { FilesInfosService } from 'app/entities/files-infos/files-infos.service';
import { FilesInfos } from 'app/shared/model/files-infos.model';

describe('Component Tests', () => {
  describe('FilesInfos Management Update Component', () => {
    let comp: FilesInfosUpdateComponent;
    let fixture: ComponentFixture<FilesInfosUpdateComponent>;
    let service: FilesInfosService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [FilesInfosUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(FilesInfosUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FilesInfosUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FilesInfosService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FilesInfos(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new FilesInfos();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
