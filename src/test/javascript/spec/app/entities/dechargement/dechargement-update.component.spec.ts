import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { DechargementUpdateComponent } from 'app/entities/dechargement/dechargement-update.component';
import { DechargementService } from 'app/entities/dechargement/dechargement.service';
import { Dechargement } from 'app/shared/model/dechargement.model';

describe('Component Tests', () => {
  describe('Dechargement Management Update Component', () => {
    let comp: DechargementUpdateComponent;
    let fixture: ComponentFixture<DechargementUpdateComponent>;
    let service: DechargementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [DechargementUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DechargementUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DechargementUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DechargementService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Dechargement(123);
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
        const entity = new Dechargement();
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
