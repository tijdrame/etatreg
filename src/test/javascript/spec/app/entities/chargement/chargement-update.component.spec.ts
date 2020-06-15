import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { ChargementUpdateComponent } from 'app/entities/chargement/chargement-update.component';
import { ChargementService } from 'app/entities/chargement/chargement.service';
import { Chargement } from 'app/shared/model/chargement.model';

describe('Component Tests', () => {
  describe('Chargement Management Update Component', () => {
    let comp: ChargementUpdateComponent;
    let fixture: ComponentFixture<ChargementUpdateComponent>;
    let service: ChargementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [ChargementUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ChargementUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ChargementUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ChargementService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Chargement(123);
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
        const entity = new Chargement();
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
