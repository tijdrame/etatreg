import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { NatureDebiteurUpdateComponent } from 'app/entities/nature-debiteur/nature-debiteur-update.component';
import { NatureDebiteurService } from 'app/entities/nature-debiteur/nature-debiteur.service';
import { NatureDebiteur } from 'app/shared/model/nature-debiteur.model';

describe('Component Tests', () => {
  describe('NatureDebiteur Management Update Component', () => {
    let comp: NatureDebiteurUpdateComponent;
    let fixture: ComponentFixture<NatureDebiteurUpdateComponent>;
    let service: NatureDebiteurService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [NatureDebiteurUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(NatureDebiteurUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NatureDebiteurUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NatureDebiteurService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NatureDebiteur(123);
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
        const entity = new NatureDebiteur();
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
