import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { ObjetCreditUpdateComponent } from 'app/entities/objet-credit/objet-credit-update.component';
import { ObjetCreditService } from 'app/entities/objet-credit/objet-credit.service';
import { ObjetCredit } from 'app/shared/model/objet-credit.model';

describe('Component Tests', () => {
  describe('ObjetCredit Management Update Component', () => {
    let comp: ObjetCreditUpdateComponent;
    let fixture: ComponentFixture<ObjetCreditUpdateComponent>;
    let service: ObjetCreditService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [ObjetCreditUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ObjetCreditUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ObjetCreditUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ObjetCreditService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ObjetCredit(123);
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
        const entity = new ObjetCredit();
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
