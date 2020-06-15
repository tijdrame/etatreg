import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { BduEffetUpdateComponent } from 'app/entities/bdu-effet/bdu-effet-update.component';
import { BduEffetService } from 'app/entities/bdu-effet/bdu-effet.service';
import { BduEffet } from 'app/shared/model/bdu-effet.model';

describe('Component Tests', () => {
  describe('BduEffet Management Update Component', () => {
    let comp: BduEffetUpdateComponent;
    let fixture: ComponentFixture<BduEffetUpdateComponent>;
    let service: BduEffetService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [BduEffetUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BduEffetUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BduEffetUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BduEffetService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BduEffet(123);
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
        const entity = new BduEffet();
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
